package com.lebahakatsuki.menuapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.model.FoodDrink

class ListDrinkDetailAdapter(private val listDrink: ArrayList<FoodDrink>) : RecyclerView.Adapter<ListDrinkDetailAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_drink_detail, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val foodDrink = listDrink[position]

        Glide.with(holder.itemView.context).load(foodDrink.photo).apply(RequestOptions().override(350,550)).into(holder.ivPhoto)

        holder.tvDrink.text = foodDrink.name
        holder.tvPrice.text = foodDrink.price
    }

    override fun getItemCount(): Int {
        return listDrink.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var ivPhoto: ImageView = itemView.findViewById(R.id.ivDrink)
        var tvDrink: TextView = itemView.findViewById(R.id.tvDrink)
        var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }
}