package com.fdev.technogram.repository

sealed class Event<Data>{

    data class OnSuccess<Data>(val data : Data , val message : String) : Event<Data>()

    data class OnFailure<Data>(val message : String) : Event<Data>()


}