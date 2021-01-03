package com.fdev.technogram.util

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LazyListState(
        val index : Int = 0,
        val offset : Int =0
) : Parcelable