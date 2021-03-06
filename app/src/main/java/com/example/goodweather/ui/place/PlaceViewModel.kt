package com.example.goodweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.goodweather.logic.Repository
import com.example.goodweather.logic.model.Place

class PlaceViewModel :ViewModel(){
    private val searchLiveData  = MutableLiveData<String>()
    val placeList = ArrayList<Place>()
    val placeLiveData = Transformations.switchMap(searchLiveData){
        query->Repository.searchPlaces(query)
    }
    fun searchPlaces(query:String){
        searchLiveData.value =query
    }

    fun sacedPlace(place: Place) = Repository.savePlace(place)

    fun getSavedPlace () = Repository.getSavedPlace()
    fun isSavedPlace()  =Repository.isPlaceSaved()
}