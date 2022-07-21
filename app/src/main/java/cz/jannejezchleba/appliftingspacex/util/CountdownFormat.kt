package cz.jannejezchleba.appliftingspacex.util

import java.util.concurrent.TimeUnit

object CountdownFormat {
    private const val TIME_FORMAT = "%02d:%02d:%02d"

    //convert time to milli seconds
    fun formatTime(millis: Long): String {
        val days = TimeUnit.MILLISECONDS.toDays(millis)
        val hours = TimeUnit.MILLISECONDS.toHours(millis) % 24 + (days * 24)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60

        return String.format(TIME_FORMAT, hours, minutes, seconds)
    }
}