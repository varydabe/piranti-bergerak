package com.lebahakatsuki.menuapp.ui.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lebahakatsuki.menuapp.ui.main.view.MainActivity
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.data.`interface`.FoodDrinkListener
import com.lebahakatsuki.menuapp.data.model.FoodDrink
import kotlinx.coroutines.selects.select

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

        if (foodDrink.isSelected){
            holder.cardview.setBackgroundResource(R.drawable.custom_cardview)
        }
    }

    override fun getItemCount(): Int {
        return listDrink.size
    }

    fun getSelectedFoodDrink(): List<FoodDrink>{
        var selectedFoodDrink= ArrayList<FoodDrink>()
        for (foodDrink: FoodDrink in listDrink){
            if (foodDrink.isSelected){
                selectedFoodDrink.add(foodDrink)
            }
        }
        return selectedFoodDrink
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var ivPhoto: ImageView = itemView.findViewById(R.id.ivDrink)
        var tvDrink: TextView = itemView.findViewById(R.id.tvDrink)
        var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        var cardview: CardView = itemView.findViewById(R.id.cardviewDrinkDetail)
    }
}