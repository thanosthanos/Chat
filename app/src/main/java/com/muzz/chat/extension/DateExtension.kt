package com.muzz.chat.extension

import android.annotation.SuppressLint
import timber.log.Timber
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


const val DayTimeFormatter = "EEEE HH:mm"

@SuppressLint("SimpleDateFormat")
fun Long.toDayTimeFormat(): String? {
    try {
        val date = Date(this)
        val outputFormat: DateFormat = SimpleDateFormat(DayTimeFormatter)
        return outputFormat.format(date)
    } catch (exc: Exception) {
        Timber.e(exc)
    }

    return null
}
