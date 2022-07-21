package cz.jannejezchleba.appliftingspacex.data.repository

import cz.jannejezchleba.appliftingspacex.data.model.*
import cz.jannejezchleba.appliftingspacex.data.paging.PaginatedLaunches

interface SpaceXRepository {
    suspend fun getCompany() : Company

    suspend fun getAllRockets() : List<Rocket.RocketThumbnail>

    suspend fun getRocketById(id: String) : Rocket

    suspend fun getAllPossibleFilterRockets() : List<Launch.LaunchRocket>

    suspend fun getFilteredLaunches(page: Int, limit: Int, filter: LaunchesFilter) : PaginatedLaunches

    suspend fun getNextLaunch() : NextLaunch
}