package com.example.goodweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


object GoodWeatherNetwork {

    private val placeService = ServiceCreator.create(PlaceService::class.java)

    suspend fun searchPlaces(query :String )= placeService.searchPlaces(query).await()

    private val weatherService = ServiceCreator.create(WeatherService::class.java)



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

    suspend fun getDailyWeather(lng:String ,lat:String) = weatherService.getDailyWeather(lng,lat).await()


    suspend fun getRealtimeWeather(lng: String,lat: String)= weatherService.getRealtimeWeather(lng,lat).await()
}