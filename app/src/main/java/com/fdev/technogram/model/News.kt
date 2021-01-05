package com.fdev.technogram.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class News(

    val id : Int,

    val title : String,

    // Represent url to image
    val headerImg : String,

    val article : String,

    val publishTime : Long,

    val likes : Int,

    val reads : Int,

    val writer : String,

    val category : String,

    val preview : String
) : Parcelable