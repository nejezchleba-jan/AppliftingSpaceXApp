package cz.jannejezchleba.appliftingspacex.data.model

import cz.jannejezchleba.appliftingspacex.data.model.enum.LaunchSuccess
import java.time.LocalDate

data class LaunchesFilter(
    var success: LaunchSuccess = LaunchSuccess.ANY,
    var dateFrom: String? = null,
    var dateTo: String? = null,
    var rocketId: String? = null,
    var launchSortAsc: Boolean = false
) {
    fun getLocalDateFrom(): LocalDate? {
        return if (!dateFrom.isNullOrEmpty()) {
            LocalDate.parse(dateFrom)
        } else {
            null
        }
    }
    fun getLocalDateTo(): LocalDate? {
        return if (!dateTo.isNullOrEmpty()) {
            LocalDate.parse(dateTo)
        } else {
            null
        }
    }
}