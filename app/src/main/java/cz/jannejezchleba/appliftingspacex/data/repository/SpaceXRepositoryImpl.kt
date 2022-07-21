package cz.jannejezchleba.appliftingspacex.data.repository

import cz.jannejezchleba.appliftingspacex.data.model.*
import cz.jannejezchleba.appliftingspacex.data.network.NetworkException
import cz.jannejezchleba.appliftingspacex.data.paging.PaginatedLaunches
import cz.jannejezchleba.appliftingspacex.data.service.SpaceXApiService
import okhttp3.CacheControl
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SpaceXRepositoryImpl @Inject constructor(
    private val api: SpaceXApiService
    ) : SpaceXRepository {

    override suspend fun getCompany(): Company {
        val result = api.getCompanyInfo()
        if(result.isSuccessful) {
            return result.body() ?: throw NetworkException("No body in response.", result.code())
        }  else {
            throw NetworkException(result.errorBody().toString(), result.code())
        }
    }

    override suspend fun getAllRockets(): List<Rocket.RocketThumbnail>  {
        val result = api.getAllRockets()
        if(result.isSuccessful) {
            return result.body() ?: throw NetworkException("No body in response.", result.code())
        }  else {
            throw NetworkException(result.errorBody().toString(), result.code())
        }
    }
    override suspend fun getRocketById(id: String): Rocket {
        val result = api.getRocketById(id)
        if(result.isSuccessful) {
            return result.body() ?: throw NetworkException("No body in response.", result.code())
        }  else {
            throw NetworkException(result.errorBody().toString(), result.code())
        }
    }

    override suspend fun getAllPossibleFilterRockets(): List<Launch.LaunchRocket> {
        val result = api.getPossibleFilterRockets()
        if(result.isSuccessful) {
            return result.body() ?: throw NetworkException("No body in response.", result.code())
        }  else {
            throw NetworkException(result.errorBody().toString(), result.code())
        }
    }

    override suspend fun getFilteredLaunches(page: Int, limit: Int, filter: LaunchesFilter): PaginatedLaunches {
        val query = FilterQuery(
            filter.success,
            DateSpanFilter(greaterThan = filter.dateFrom, lessThan = filter.dateTo),
            filter.rocketId
        )
        val options = QueryOptions(limit = limit, page = page)
        options.setSort(filter.launchSortAsc)

        val result = api.getFilteredLaunches(PaginatedQuery(query, options))
        if(result.isSuccessful) {
            return result.body() ?: throw NetworkException(responseMessage = "No body in response.", result.code())
        }  else {
            throw NetworkException(result.errorBody().toString(), result.code())
        }
    }

    override suspend fun getNextLaunch(forceRequest: Boolean): NextLaunch {
        val result = if (forceRequest) {
            api.getNextLaunchWithoutCache()
        } else {
            api.getNextLaunch()
        }
        if(result.isSuccessful) {
            return result.body() ?: throw NetworkException("No body in response.", result.code())
        }  else {
            throw NetworkException(result.errorBody().toString(), result.code())
        }
    }
}