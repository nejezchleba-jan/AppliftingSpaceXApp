package cz.jannejezchleba.appliftingspacex.data.model


import com.google.gson.annotations.SerializedName
import cz.jannejezchleba.appliftingspacex.data.model.enum.LaunchSuccess

//Classes for serialization of custom queries using /query endpoint

const val MIN_DATE = "2000-01-01"
const val MAX_DATE = "2100-01-01"

data class PaginatedQuery(
    val query: FilterQuery = FilterQuery(),
    val options: QueryOptions = QueryOptions()
)

// Mongoose query for filtering by launch success, launch time, rocket name
data class FilterQuery(
    val success: LaunchSuccess = LaunchSuccess.ANY,
    @field:SerializedName("date_local") val dateLocal: DateSpanFilter = DateSpanFilter(),
    @field:SerializedName("rocket_name") val rocket: String? = null
)

data class DateSpanFilter(
    @field:SerializedName("$" + "gt") val greaterThan: String? = MIN_DATE,
    @field:SerializedName("$" + "lt") val lessThan: String? = MAX_DATE,
)

// Options for query: populating extra fields, pagination vars and sorting
data class QueryOptions(
    val populate: List<PopulateRocket> = listOf(PopulateRocket()),
    val page: Int = 1,
    val limit: Int = 10,
    var sort: Sort = Sort()
) {
    fun setSort(isAsc: Boolean) {
        sort = if (isAsc) Sort(1) else Sort(-1)
    }
}

//Sort paginated results
data class Sort(
    @field:SerializedName("date_local") var sortType: Int = -1,
)

//Get Rocket info in query
data class PopulateRocket(
    val path: String = "rocket",
    val select: Select = Select()
)

data class Select(val name: Int = 1)




