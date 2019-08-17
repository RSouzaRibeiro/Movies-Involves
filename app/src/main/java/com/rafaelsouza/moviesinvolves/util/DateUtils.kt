package com.rafaelsouza.moviesinvolves.util

import java.text.ParseException
import java.text.SimpleDateFormat

class DateUtils {

    private val SERVER_DATE_FORMAT = "yyyy-MM-dd"
    private val HUMAN_DATE_FORMAT = "dd/MM/yyyy"


    @Throws(ParseException::class)
    fun formatDate(date: String): String {

        val initDate = SimpleDateFormat(SERVER_DATE_FORMAT).parse(date)
        val formatter = SimpleDateFormat(HUMAN_DATE_FORMAT)

        return formatter.format(initDate)
    }
}