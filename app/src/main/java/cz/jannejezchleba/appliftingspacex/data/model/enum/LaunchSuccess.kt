package cz.jannejezchleba.appliftingspacex.data.model.enum

// Enum for use in filter success field in api call
enum class LaunchSuccess(val text: String) {
    ANY("Any"), // Any value in query
    SUCCESS("Success"), // TRUE value in query
    FAIL("Fail"), // FALSE value in query
    TBD("TBD"); // NULL value in query

    companion object {
        fun getByText(text: String) : LaunchSuccess {
            return values().first { it.text == text }
        }

        fun getByValue(value: Boolean?) : LaunchSuccess {
            return when(value) {
                true -> SUCCESS
                false -> FAIL
                null -> TBD
            }
        }
    }
}