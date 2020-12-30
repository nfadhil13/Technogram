package com.fdev.technogram.util

import org.jsoup.Jsoup


fun htmlToText(htmlString : String, takenCharacter : Int) : String{
    val noTagString = Jsoup.parse(htmlString).text()
    return  noTagString.substring(0 , takenCharacter)
}