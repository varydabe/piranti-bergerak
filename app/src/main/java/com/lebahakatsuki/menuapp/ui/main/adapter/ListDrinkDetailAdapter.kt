package com.lebahakatsuki.menuapp.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.data.model.Menu
import kotlinx.android.synthetic.main.list_food_detail.view.*
import java.text.NumberFormat
import java.util.*

//Adapter for List Drink DETAIL
class ListDrinkDetailAdapter: RecyclerView.Adapter<ListDrinkDetailAdapter.ListViewHolder>() {
    private var listDrink = emptyList<Menu>()
    private var selectedPosition = -1
    var onItemClick: ((Menu?, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_drink_detail, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        return holder.bind(listDrink[position], position, selectedPosition, null)
    }

    override fun getItemCount(): Int {
        return listDrink.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var ivPhoto: ImageView = itemView.findViewById(R.id.ivDrink)        //ivPhoto not used -> no photo from API
        var tvDrink: TextView = itemView.findViewById(R.id.tvDrink)
        var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)

        //Make background element to show when clicked
        fun bind(data: Menu?, position: Int, selectedPos: Int?, onClickListener: ((Menu?, Int) -> Unit)?) {
            Log.d("selectedPos and pos", "$position, $selectedPos")
            if (position == selectedPosition) itemView.itemBackground.visibility = View.VISIBLE
            else itemView.itemBackground.visibility = View.INVISIBLE
            itemView.setOnClickListener {
                onItemClick?.invoke(data, position)
                Log.d("MENU SELECTED", data.toString())
            }

            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            val price = formatRupiah.format(data?.harga).replace("Rp", "Rp ")

            tvDrink.text = data?.nama
            tvPrice.text = price
        }
    }

    //Set data when changes
    fun setData(listDrink: List<Menu>){
        this.listDrink = listDrink
        notifyDataSetChanged()
    }

    fun getSelectedPos() = selectedPosition

    //Select item position
    fun setSelectedPos(position: Int){
        Log.d("setselectedPos", position.toString())
        this.selectedPosition = position
        notifyDataSetChanged()
    }
}