package cz.jannejezchleba.appliftingspacex.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cz.jannejezchleba.appliftingspacex.data.model.Launch
import cz.jannejezchleba.appliftingspacex.data.model.LaunchesFilter
import cz.jannejezchleba.appliftingspacex.data.repository.SpaceXRepository
import retrofit2.HttpException
import java.io.IOException

class LaunchesPagingSource constructor(
    private val repo: SpaceXRepository,
    private var appliedFilter: LaunchesFilter,
) : PagingSource<Int, Launch>() {

    private val ITEMS_PER_PAGE = 10
    private var nextPage = 1


    // Loads items using paging system
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Launch> {
        return try {
            nextPage = params.key ?: 1

            val launchesList = repo.getFilteredLaunches(
                nextPage,
                ITEMS_PER_PAGE,
                appliedFilter
            )

            LoadResult.Page(
                data = launchesList.docs,
                prevKey = if (launchesList.hasPrevPage) launchesList.prevPage else null,
                nextKey = if (launchesList.hasNextPage) launchesList.nextPage else null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Launch>): Int? {
        return null //Go Always to the top of the list after invalidation
    }
}