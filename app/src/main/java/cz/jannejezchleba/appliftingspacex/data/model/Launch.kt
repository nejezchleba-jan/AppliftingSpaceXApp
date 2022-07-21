package cz.jannejezchleba.appliftingspacex.data.model

import com.google.gson.annotations.SerializedName
import cz.jannejezchleba.appliftingspacex.data.model.enum.LaunchSuccess
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

data class Launch(
    val id: String,
    val name: String,
    val details: String?,
    val success: Boolean?,
    val links: LaunchLinks,
    val rocket: LaunchRocket,
    @field:SerializedName("flight_number")val flightNumber: String,
    @field:SerializedName( "date_local")val dateLocal: String?,
) {
    fun getFormattedLaunchDate() : String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return OffsetDateTime.parse(dateLocal).format(formatter).toString()
    }

    fun getTextForLaunchResult() : String {
        return when(success) {
            true -> LaunchSuccess.SUCCESS.text
            false -> LaunchSuccess.FAIL.text
            else -> LaunchSuccess.TBD.text
        }
    }

    data class LaunchLinks(
        val patch: Patch?,
        val article: String?,
        val wikipedia: String?,
        val webcast: String?,
    ) {
        fun linksArePresent() : Boolean {
            return !article.isNullOrEmpty() || !wikipedia.isNullOrEmpty() || !webcast.isNullOrEmpty()
        }
    }


    data class LaunchRocket(
        val id: String?,
        val name: String?,
    )


    data class Patch(
        val small: String?,
        val large: String?
    )
}




