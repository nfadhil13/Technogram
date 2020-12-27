package com.fdev.technogram.util

import java.text.SimpleDateFormat
import java.util.Date


// This will return a date with this form : Thu 12/24/2020 14:58
fun getFullDateFromLong(date : Long) : String{
    val date = Date(date)
    val format = SimpleDateFormat("EEE dd/MM/yyyy h:mm")
    return format.format(date).toString()
}