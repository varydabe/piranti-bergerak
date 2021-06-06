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
import java.text.NumberFormat
import java.util.*

//Adapter of List Food
class ListFoodAdapter() : RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {
    private var listFood = emptyList<Menu>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_food, parent, false)
        return ListViewHolder(view)
    }

    //Bind data to view holder
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val foodDrink = listFood[position]
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        val price = formatRupiah.format(foodDrink.harga).replace("Rp", "Rp ")

        holder.tvFood.text = foodDrink.nama
        holder.tvPrice.text = price
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var ivPhoto: ImageView = itemView.findViewById(R.id.ivFood)
        var tvFood: TextView = itemView.findViewById(R.id.tvFood)
        var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }

    //Set data when changes
    fun setData(listFood: List<Menu>){
        this.listFood = listFood
        notifyDataSetChanged()
    }
}