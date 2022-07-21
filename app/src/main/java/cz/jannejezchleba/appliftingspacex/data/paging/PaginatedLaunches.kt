package cz.jannejezchleba.appliftingspacex.data.paging

import cz.jannejezchleba.appliftingspacex.data.model.Launch

data class PaginatedLaunches(
    val docs: List<Launch>,
    val totalDocs: Int,
    val limit: Int,
    val totalPages: Int,
    val page: Int,
    val pagingCounter: Int,
    val hasPrevPage: Boolean,
    val hasNextPage: Boolean,
    val prevPage: Int?,
    val nextPage: Int?
)