package com.rafaelsouza.moviesinvolves.extension

import java.lang.Double.*
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

fun Long.convertMinutsToHour(): String {

    return String.format(
        "%d H, %d min",
        TimeUnit.MINUTES.toHours(this),
        TimeUnit.MINUTES.toMinutes(this) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MINUTES.toHours(this))
    )

}

fun Long. currencyFormat():String{
    val formatter = DecimalFormat("$ ###,###,##0.00")
    return formatter.format(parseDouble(this.toString()))
}