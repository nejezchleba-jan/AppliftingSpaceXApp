package cz.jannejezchleba.appliftingspacex

import cz.jannejezchleba.appliftingspacex.data.model.DateSpanFilter
import cz.jannejezchleba.appliftingspacex.data.model.FilterQuery
import cz.jannejezchleba.appliftingspacex.data.model.enum.LaunchSuccess
import cz.jannejezchleba.appliftingspacex.data.network.CustomFilterQuerySerializer
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*


class ExampleUnitTest {
    @Test
    fun custom_serialization_returns_correct_object() {
        val unit = CustomFilterQuerySerializer()
        val testFilterQuery = FilterQuery(
            LaunchSuccess.SUCCESS,
            DateSpanFilter("2020-11-11", "2022-12-12"),
            UUID.randomUUID().toString()
        )

        val json = unit.serialize(testFilterQuery, FilterQuery::class.java, null).asJsonObject

        assertEquals(LaunchSuccess.SUCCESS, LaunchSuccess.getByValue(json.get("success").asBoolean))
        assertEquals(testFilterQuery.rocket, json.get("rocket").asString)
        assertEquals(testFilterQuery.dateLocal.greaterThan, json.get("date_local").asJsonObject.get("$" + "gt").asString)
        assertEquals(testFilterQuery.dateLocal.lessThan, json.get("date_local").asJsonObject.get("$" + "lt").asString)
    }
}