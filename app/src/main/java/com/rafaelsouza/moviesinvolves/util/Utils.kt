package com.rafaelsouza.moviesinvolves.util


import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class Utils {

    private val SERVER_DATE_FORMAT = "yyyy-MM-dd"
    private val HUMAN_DATE_FORMAT = "dd/MM/yyyy"


    fun formatDate(date: String): String {
        return try {
            val initDate = SimpleDateFormat(SERVER_DATE_FORMAT).parse(date)
            val formatter = SimpleDateFormat(HUMAN_DATE_FORMAT)

            formatter.format(initDate)
        } catch (e: Exception) {
            ""
        }

    }


    fun convertMinutsToHour(minutes: Long): String{

       return String.format("%d H, %d min",
           TimeUnit.MINUTES.toHours(minutes),
            TimeUnit.MINUTES.toMinutes(minutes) -
                    TimeUnit.HOURS.toMinutes(TimeUnit.MINUTES.toHours(minutes))
        )

    }

    fun currencyFormat(amount: String): String {
        val formatter = DecimalFormat("$ ###,###,##0.00")
        return formatter.format(java.lang.Double.parseDouble(amount))
    }
}