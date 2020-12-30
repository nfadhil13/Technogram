package com.fdev.technogram.repository

sealed class DataState<Data>{

    data class OnSuccess<Data>(val data : Data , val message : String) : DataState<Data>()

    data class OnFailure<Data>(val message : String) : DataState<Data>()

}