package com.example.goodweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


object GoodWeatherNetwork {

    private val placeService = ServiceCreator.create(PlaceService::class.java)

    suspend fun searchPlaces(query :String )= placeService.searchPlaces(query).await()

    private suspend fun <T> Call<T>.await():T{
        return suspendCoroutine { continutaion ->
            enqueue (object : Callback<T> {
                override fun  onResponse(call:Call<T>,response: Response<T>){
                    val  body = response.body()
                    if (body!= null) continutaion.resume(body)
                    else continutaion.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    continutaion.resumeWithException(t)
                }
            })
        }
    }
}