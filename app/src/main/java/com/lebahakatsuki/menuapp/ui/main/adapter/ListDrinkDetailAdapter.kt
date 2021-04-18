package com.lebahakatsuki.menuapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lebahakatsuki.menuapp.ui.main.view.MainActivity
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.data.`interface`.FoodDrinkListener
import com.lebahakatsuki.menuapp.data.model.DrinkEntity
import com.lebahakatsuki.menuapp.data.model.FoodDrink
import kotlinx.coroutines.selects.select

class ListDrinkDetailAdapter: RecyclerView.Adapter<ListDrinkDetailAdapter.ListViewHolder>() {
    private var listDrink = emptyList<DrinkEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_drink_detail, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val foodDrink = listDrink[position]

        //Glide.with(holder.itemView.context).load(foodDrink.photo).apply(RequestOptions().override(350,550)).into(holder.ivPhoto)

        holder.tvDrink.text = foodDrink.drinkName
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

    fun setData(listDrink: List<DrinkEntity>){
        this.listDrink = listDrink
        notifyDataSetChanged()
    }
}