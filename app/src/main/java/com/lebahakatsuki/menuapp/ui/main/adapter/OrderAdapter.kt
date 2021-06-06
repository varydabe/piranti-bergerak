package com.lebahakatsuki.menuapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.data.model.OrderEntity
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

//Adapter for Order
class OrderAdapter(): RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    private var listOrder = emptyList<OrderEntity>()

    //get view holder
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        var timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        var tvFood: TextView = itemView.findViewById(R.id.tvFood)
        var tvDrink: TextView = itemView.findViewById(R.id.tvDrink)
        var tv1: TextView = itemView.findViewById(R.id.tv1)
        var tv2: TextView = itemView.findViewById(R.id.tv2)
        var tvFoodPrice: TextView = itemView.findViewById(R.id.tvFoodPrice)
        var tvDrinkPrice: TextView = itemView.findViewById(R.id.tvDrinkPrice)
        var totalPriceTextView:TextView = itemView.findViewById(R.id.totalPriceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.list_order,
            parent,
            false
        )
        return ViewHolder(view)
    }

    //bind data to view holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOrder[position]

        //set time of order
        val date = Date(item.orderDate)
        val locale = Locale("id", "ID")
        val sdfDate = SimpleDateFormat("EEEE, d MMMM yyyy", locale)
        sdfDate.timeZone = TimeZone.getTimeZone("GMT+7")
        val sdfTime = SimpleDateFormat("HH:mm")
        sdfTime.timeZone = TimeZone.getTimeZone("GMT+7")

        //removing "Rp" from price.
        val formatRupiah = NumberFormat.getCurrencyInstance(locale)
        val foodPrice = formatRupiah.format(item.foodPrice).replace("Rp", "")
        val drinkPrice = formatRupiah.format(item.drinkPrice).replace("Rp", "")
        val totalPrice = formatRupiah.format(item.totalPrice).replace("Rp", "")

        //Bind to view
        holder.dateTextView.text = sdfDate.format(date)
        holder.timeTextView.text = sdfTime.format(date)
        if (item.foodName == "") {
            holder.tvFoodPrice.visibility = View.GONE
            holder.tvFood.visibility = View.GONE
            holder.tv1.visibility = View.GONE
        } else {
            holder.tvFood.text = item.foodName
            holder.tvFoodPrice.text = foodPrice
        }

        if (item.drinkName == "") {
            holder.tvDrinkPrice.visibility = View.GONE
            holder.tvDrink.visibility = View.GONE
            holder.tv2.visibility = View.GONE
        } else {
            holder.tvDrink.text = item.drinkName
            holder.tvDrinkPrice.text = drinkPrice
        }
        holder.totalPriceTextView.text = totalPrice
    }

    override fun getItemCount(): Int {
        return listOrder.size
    }

    fun setData(listOrder: List<OrderEntity>) {
        this.listOrder = listOrder
        notifyDataSetChanged()
    }
}