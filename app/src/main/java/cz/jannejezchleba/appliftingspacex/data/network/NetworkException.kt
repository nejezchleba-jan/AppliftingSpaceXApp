package cz.jannejezchleba.appliftingspacex.data.network

class NetworkException (
    val responseMessage: String? = null,
    val responseCode: Int = -1
) : Exception() {
}