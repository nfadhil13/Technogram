package com.fdev.technogram.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date


object DateUtil {

    // This will return a date with this form : Thu 12/24/2020 14:58
    fun getFullDateFromLong(dateInLong : Long) : String{
        val date = Date(dateInLong)
        val format = SimpleDateFormat("EEE dd/MM/yyyy h:mm")
        return format.format(date).toString()
    }

    //This will return date with this form : Dec 20
    fun getMonthAndDate(dateInLong : Long) : String{
        val date = Date(dateInLong)
        val format = SimpleDateFormat("MMM dd")
        return format.format(date).toString()
    }


    // Parse from date string
    fun getDateFromString(dateInString: String): Long {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return try {
            val date = format.parse(dateInString)
            date?.let{
                getMonthAndDate(it.time)
            }
            date?.time ?: throw Exception("Fail to parse")
        } catch (e: ParseException) {
            -1L
        }

    }
}


