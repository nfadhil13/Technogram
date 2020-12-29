//package com.fdev.technogram.repository
//
//data class DataState<T>(
//        val message : String,
//        val data : T? = null ,
//        val event : Event
//){
//    companion object {
//        fun <T> onError(
//            message: String
//        ) : DataState<T>{
//            return DataState<T>(
//                    message =  message,
//                    event = Event.ERROR
//            )
//        }
//    }
//}