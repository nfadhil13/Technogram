package com.fdev.technogram.util

import com.fdev.technogram.datasource.network.NetworkErrorConst
import kotlinx.coroutines.TimeoutCancellationException
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException


fun errorHandler(exception: Exception) : String{
    var message = exception.message ?: NetworkErrorConst.UNKNOWN_ERROR
    println(message)
    when(exception){
        is HttpException -> {
            message = exception.message()
            exception.response()?.errorBody()?.let{
                message = parseRetrofitError(it , exception.code())
            }

        }
        is TimeoutCancellationException -> {
            message = NetworkErrorConst.TIMEOUT_ERROR
        }
    }
    return message
}

/*
This is a response handle to specific api (it must be changed if you change the api) ,
here is how the response look like

Response can has 2 type of cause , array of warn or  only string


1. Array of warning :

{
    "message": "Input invalid",
    "error": 422,
    "cause": [
        {
            "value": "",
            "msg": "Password tidak boleh kosong.",
            "param": "password",
            "location": "body"
        }
    ]
}

2. String

{
    "message": "Unexpected token } in JSON at position 44",
    "error": 400,
    "cause": "Unknown"
}

 */
fun parseRetrofitError(errorBody: ResponseBody, code: Int?) : String{
    return try{
        val response = JSONObject(errorBody.string())
        var message = response.getString("message")
        var cause = ""

        //Get cause with any type
        val plainCause = response.get("cause")
        println(plainCause::class.java)
        if(plainCause is String){
            cause = plainCause
        }else if(plainCause is JSONArray){
            cause = plainCause.getJSONObject(0).getString("msg")
        }
        message = if(code == 500 && cause.isNotBlank())"$message caused by ${NetworkErrorConst.INTERNAL_SERVER_ERROR}" else "$message caused by $cause"
        message
    }catch(exception : Exception){
        exception.message ?: NetworkErrorConst.UNKNOWN_ERROR
    }
}