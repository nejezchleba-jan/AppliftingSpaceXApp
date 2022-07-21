package cz.jannejezchleba.appliftingspacex.data.network

import com.google.gson.*
import cz.jannejezchleba.appliftingspacex.data.model.DateSpanFilter
import cz.jannejezchleba.appliftingspacex.data.model.FilterQuery
import cz.jannejezchleba.appliftingspacex.data.model.MAX_DATE
import cz.jannejezchleba.appliftingspacex.data.model.MIN_DATE
import cz.jannejezchleba.appliftingspacex.data.model.enum.LaunchSuccess
import java.lang.reflect.Type

class CustomFilterQuerySerializer : JsonSerializer<FilterQuery> {
    override fun serialize(
        src: FilterQuery,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val createdJSON = JsonObject()
        when (src.success) {
            LaunchSuccess.SUCCESS -> createdJSON.addProperty("success", true)
            LaunchSuccess.FAIL -> createdJSON.addProperty("success", false)
            LaunchSuccess.TBD -> createdJSON.add("success", JsonNull.INSTANCE)
            else -> {}
        }

        val greaterThan = src.dateLocal.greaterThan?.let { src.dateLocal.greaterThan } ?: MIN_DATE
        val lessThan = src.dateLocal.lessThan?.let { src.dateLocal.lessThan } ?: MAX_DATE

        createdJSON.add("date_local", gson.toJsonTree(DateSpanFilter(greaterThan, lessThan)))

        src.rocket?.let { createdJSON.addProperty("rocket", src.rocket) }

        return createdJSON
    }
}