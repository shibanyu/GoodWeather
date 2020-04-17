package com.example.goodweather.ui.place

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goodweather.R
import com.example.goodweather.logic.model.Place
import com.example.goodweather.ui.weather.WeatherActivity

class PlaceAdapter(private val fragment:PlaceFragment,private val placeList:List<Place>):RecyclerView.Adapter<PlaceAdapter.ViewHolder> (){

    inner class ViewHolder (view:View):RecyclerView.ViewHolder(view){
        val placeAddress :TextView = view.findViewById(R.id.placeAddress)
        val placeName:TextView = view.findViewById(R.id.placeName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_item, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val place = placeList[position]
            val intent = Intent(parent.context,WeatherActivity::class.java).apply {
                putExtra("location_lng",place.location.lng)
                putExtra("location_lat",place.location.lat)
                putExtra("place_name",place.name)
            }
            fragment.viewModel.sacedPlace(place)
            fragment.startActivity(intent)
            fragment.activity?.finish()
        }
        return holder
    }

    override fun getItemCount() = placeList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val  place = placeList[position]
        holder.placeName.text =place.name
        holder.placeAddress.text = place.address
    }

}