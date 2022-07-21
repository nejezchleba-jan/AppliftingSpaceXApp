package cz.jannejezchleba.appliftingspacex.data.service

import cz.jannejezchleba.appliftingspacex.data.model.*
import cz.jannejezchleba.appliftingspacex.data.paging.PaginatedLaunches
import retrofit2.Response
import retrofit2.http.*


interface SpaceXApiService {
    @GET("v4/company")
    suspend fun getCompanyInfo(): Response<Company>

    @GET("v4/rockets")
    suspend fun getAllRockets(): Response<List<Rocket.RocketThumbnail>>

    @GET("v4/rockets/{id}")
    suspend fun getRocketById(@Path("id") id: String): Response<Rocket>

    @GET("v4/rockets")
    suspend fun getPossibleFilterRockets(): Response<List<Launch.LaunchRocket>>

    @Headers("Content-Type: application/json")
    @POST("v5/launches/query")
    suspend fun getFilteredLaunches(@Body query: PaginatedQuery): Response<PaginatedLaunches>

    @GET("v5/launches/next")
    suspend fun getNextLaunch(): Response<NextLaunch>

}