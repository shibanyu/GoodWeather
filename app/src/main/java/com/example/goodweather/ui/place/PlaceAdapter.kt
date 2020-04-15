package com.example.goodweather.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goodweather.R
import com.example.goodweather.logic.model.Place

class PlaceAdapter(private val fragment:PlaceFragment,private val placeList:List<Place>):RecyclerView.Adapter<PlaceAdapter.ViewHolder> (){

    inner class ViewHolder (view:View):RecyclerView.ViewHolder(view){
        val placeAddress :TextView = view.findViewById(R.id.placeAddress)
        val placeName:TextView = view.findViewById(R.id.placeName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = placeList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val  place = placeList[position]
        holder.placeName.text =place.name
        holder.placeAddress.text = place.address
    }

}