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

//Adapter of List Food DETAIL
class ListFoodDetailAdapter: RecyclerView.Adapter<ListFoodDetailAdapter.ListViewHolder>() {
    private var listFood = emptyList<Menu>()
    private var selectedPosition = -1
    var onItemClick: ((Menu?, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_food_detail, parent, false)
        return ListViewHolder(view)
    }

    //bind view holder based on position and selected position.
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        return holder.bind(listFood[position], position, selectedPosition, null)
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    //Selected position will have background visible (shining) :D
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var ivPhoto: ImageView = itemView.findViewById(R.id.ivFood)
        var tvFood: TextView = itemView.findViewById(R.id.tvFood)
        var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)

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

            tvFood.text = data?.nama
            tvPrice.text = price
        }
    }

    fun setData(listFood: List<Menu>){
        this.listFood = listFood
        notifyDataSetChanged()
    }

    fun getSelectedPos() = selectedPosition

    fun setSelectedPos(position: Int){
        Log.d("setselectedPos", position.toString())
        this.selectedPosition = position
        notifyDataSetChanged()
    }
}
