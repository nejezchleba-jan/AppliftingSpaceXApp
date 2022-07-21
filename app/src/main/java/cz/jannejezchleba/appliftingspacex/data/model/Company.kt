package cz.jannejezchleba.appliftingspacex.data.model

data class Company(
    val name: String,
    val ceo: String,
    val coo: String,
    val valuation: Long,
    val summary: String,
    val founded: String,
    val employees: Int,
    val headquarters: Headquarters,
    val links: Links
) {
    fun getFormattedValuation(): String {
        return "${valuation / 1000000000}"
    }

    fun getFullLocation(): String {
        return "${headquarters.address}, ${headquarters.city}, ${headquarters.state}"
    }

    data class Headquarters(
        val address: String,
        val city: String,
        val state: String
    )

    data class Links(
        val website: String,
        val flickr: String,
        val twitter: String
    )
}




