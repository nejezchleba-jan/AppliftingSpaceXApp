package cz.jannejezchleba.appliftingspacex.data.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import cz.jannejezchleba.appliftingspacex.data.model.LaunchesFilter
import cz.jannejezchleba.appliftingspacex.data.model.enum.LaunchSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


//DataStore for persisting local user preferences
class LocalPreferenceDataStore(private val context: Context) {
    companion object {
        private val Context.filterDataStore: DataStore<Preferences> by preferencesDataStore("user_filter")
        val USER_FILTER_FROM = stringPreferencesKey("from_date")
        val USER_FILTER_TO = stringPreferencesKey("to_date")
        val USER_FILTER_SUCCESS = stringPreferencesKey("success")
        val USER_FILTER_ROCKET = stringPreferencesKey("rocket_id")
        val USER_FILTER_SORT = booleanPreferencesKey("sort")
    }

    suspend fun saveFilterToPreferencesStore(filter: LaunchesFilter) {
        context.filterDataStore.edit { preferences ->
            preferences[USER_FILTER_FROM] = filter.dateFrom ?: ""
            preferences[USER_FILTER_TO] = filter.dateTo ?: ""
            preferences[USER_FILTER_SUCCESS] = filter.success.text
            preferences[USER_FILTER_ROCKET] = filter.rocketId ?: ""
            preferences[USER_FILTER_SORT] = filter.launchSortAsc
        }
    }

    fun getFilterFromPreferencesStore(): Flow<LaunchesFilter> =
        context.filterDataStore.data.map { preferences ->
            val dateFrom = preferences[USER_FILTER_FROM]
            val dateTo = preferences[USER_FILTER_TO]
            val rocketId = preferences[USER_FILTER_ROCKET]
            val success = LaunchSuccess.getByText(
                preferences[USER_FILTER_SUCCESS] ?: LaunchSuccess.ANY.text
            )
            LaunchesFilter(
                dateFrom = if (dateFrom.isNullOrEmpty()) null else dateFrom,
                dateTo = if (dateTo.isNullOrEmpty()) null else dateTo,
                rocketId = if (dateTo.isNullOrEmpty()) null else rocketId,
                success = success,
                launchSortAsc = preferences[USER_FILTER_SORT] ?: false
            )
        }
}