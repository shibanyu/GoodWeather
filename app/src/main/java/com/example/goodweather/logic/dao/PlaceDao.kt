package com.example.goodweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.goodweather.GoodWeatherApplication
import com.example.goodweather.logic.model.Place
import com.google.gson.Gson

object PlaceDao {
    fun savePlace(place: Place){
        sharedPreferences().edit{
            putString("place",Gson().toJson(place))
        }
    }
    fun getSavePlace():Place{
        val placeJson = sharedPreferences().getString("place","")
        return Gson().fromJson(placeJson,Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() =
        GoodWeatherApplication.context.getSharedPreferences("good_weather",Context.MODE_PRIVATE)


}