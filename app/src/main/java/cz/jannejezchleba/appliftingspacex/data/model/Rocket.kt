package cz.jannejezchleba.appliftingspacex.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Rocket(
    val id: String,
    val name: String,
    val height: Height,
    val diameter: Diameter,
    val mass: Mass,
    val stages: Int,
    val boosters: Int,
    val wikipedia: String,
    val description: String,
    @field:SerializedName("payload_weights") val payloadWeights: List<PayloadWeight>,
    @field:SerializedName("cost_per_launch") val costPerLaunch: Long,
    @field:SerializedName("success_rate_pct") val successRate: Float,
    @field:SerializedName("first_flight") val firstFlight: String,
    @field:SerializedName("flickr_images") val images: List<String>
) {
    fun getFormattedFirstFlight(): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return LocalDate.parse(firstFlight).format(formatter).toString()
    }

    fun getFormattedCostPerLaunch(): String {
        return "${costPerLaunch / 1000000}"
    }

    data class PayloadWeight(
        val id: String,
        val name: String,
        val kg: Int,
        val lb: Int
    )


    data class Diameter(
        val meters: Float
    ) {
        fun getFormattedDiameter(): String {
            return meters.toString() + "m"
        }
    }

    data class Height(
        val meters: Float
    ) {
        fun getFormattedHeight(): String {
            return meters.toString() + "m"
        }
    }

    data class Mass(
        val kg: Int
    ) {
        fun getFormattedMass(): String {
            return (kg / 1000).toString() + "t"
        }
    }

    data class RocketThumbnail(
        val id: String,
        val name: String,
        val height: Height,
        val diameter: Diameter,
        val mass: Mass,
        @field:SerializedName("flickr_images") val images: List<String>
    )
}


