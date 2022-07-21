package cz.jannejezchleba.appliftingspacex.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import cz.jannejezchleba.appliftingspacex.data.model.Launch
import cz.jannejezchleba.appliftingspacex.data.model.LaunchesFilter
import cz.jannejezchleba.appliftingspacex.data.paging.LaunchesPagingSource
import cz.jannejezchleba.appliftingspacex.data.repository.SpaceXRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) : ViewModel() {
    var filterRockets by mutableStateOf<List<Launch.LaunchRocket>>(listOf())
    var activeFilter by mutableStateOf(LaunchesFilter())
    var initialLoadFinished by mutableStateOf(false)
    var filterApply by mutableStateOf(true)
    var resetFilter by  mutableStateOf(false)
    var isError by  mutableStateOf(false)

    var launchesFlow: Flow<PagingData<Launch>> = Pager(PagingConfig(pageSize = 10)) {
        LaunchesPagingSource(spaceXRepository, activeFilter)
    }.flow.cachedIn(viewModelScope)

    fun changeFilter(filter: LaunchesFilter, isInitialLoad: Boolean = false) {
        if (isInitialLoad) {
            loadPossibleRockets()
            initialLoadFinished = true
        }
        activeFilter = filter
        filterApply = true

    }

    private fun loadPossibleRockets() {
        isError = false
        viewModelScope.launch {
            try {
                filterRockets = spaceXRepository.getAllPossibleFilterRockets()
            } catch (e: Exception) {
                isError = true
            }
        }
    }
}