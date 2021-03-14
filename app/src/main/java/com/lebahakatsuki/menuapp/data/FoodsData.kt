package com.lebahakatsuki.menuapp.data

import com.lebahakatsuki.menuapp.R
import com.lebahakatsuki.menuapp.model.FoodDrink

object FoodsData {
    private val foodNames = arrayOf("Nasi Goreng", "Mie Goreng", "Bihun Goreng", "Ayam Goreng", "Nasi Putih")

    private val foodPrices = arrayOf("Rp 25.000,00","Rp 25.000,00","Rp 25.000,00","Rp 15.000,00","Rp 4.000,00")

    private val foodImages = intArrayOf(R.drawable.nasi_goreng, R.drawable.mie_goreng, R.drawable.bihun_goreng, R.drawable.ayam_goreng, R.drawable.nasi_putih)

    val listData: ArrayList<FoodDrink>
        get(){
            val list = arrayListOf<FoodDrink>()
            for (position in foodNames.indices){
                val foodDrink = FoodDrink()
                foodDrink.name = foodNames[position]
                foodDrink.price = foodPrices[position]
                foodDrink.photo = foodImages[position]
                list.add(foodDrink)
            }
            return list
        }
}