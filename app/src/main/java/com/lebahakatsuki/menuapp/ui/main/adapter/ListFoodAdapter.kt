package com.lebahakatsuki.menuapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.data.model.FoodDrink
import com.lebahakatsuki.menuapp.data.model.Menu

class ListFoodAdapter() : RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {
    private var listFood = emptyList<Menu>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_food, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val foodDrink = listFood[position]

        //Glide.with(holder.itemView.context).load(foodDrink.photo).apply(RequestOptions().override(350,550)).into(holder.ivPhoto)

        holder.tvFood.text = foodDrink.nama
        holder.tvPrice.text = foodDrink.harga.toString()
        //holder.tvFood.text = foodDrink.name
        //holder.tvPrice.text = foodDrink.price
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var ivPhoto: ImageView = itemView.findViewById(R.id.ivFood)
        var tvFood: TextView = itemView.findViewById(R.id.tvFood)
        var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }

    fun setData(listFood: List<Menu>){
        this.listFood = listFood
        notifyDataSetChanged()
    }
}