package cz.jannejezchleba.appliftingspacex.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

data class NextLaunch(
    val id: String,
    val name: String,
    val details: String?,
    val links: Launch.LaunchLinks,
    val rocketId: String,
    @field:SerializedName("date_utc")val dateTimeUTC: String?,
) {
    fun getLaunchLocalDateTime() : LocalDateTime {
        val timeUtc = OffsetDateTime.parse(dateTimeUTC)
        val localTime = timeUtc.withOffsetSameInstant(OffsetDateTime.now().offset)
        return localTime.toLocalDateTime()
    }

    fun getFormattedLaunchDate() : String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy\nHH:mm")
        return getLaunchLocalDateTime().format(formatter).toString()
    }
}