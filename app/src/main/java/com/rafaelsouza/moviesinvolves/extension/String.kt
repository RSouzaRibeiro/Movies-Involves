package com.rafaelsouza.moviesinvolves.extension

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

private val SERVER_DATE_FORMAT = "yyyy-MM-dd"
private val HUMAN_DATE_FORMAT = "dd/MM/yyyy"

fun String.formatDate(): String {
    return try {
        val initDate = SimpleDateFormat(SERVER_DATE_FORMAT).parse(this)
        val formatter = SimpleDateFormat(HUMAN_DATE_FORMAT)

        formatter.format(initDate)
    } catch (e: Exception) {
        ""
    }

}
