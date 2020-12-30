package com.fdev.technogram.util

import java.text.SimpleDateFormat
import java.util.Date


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