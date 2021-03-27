package com.lebahakatsuki.menuapp.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lebahakatsuki.menuapp.ui.main.view.DrinkFragment
import com.lebahakatsuki.menuapp.ui.main.view.FoodFragment

class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    private val pages = listOf(
        FoodFragment(),
        DrinkFragment()
    )

    override fun getCount(): Int {
        return pages.size
    }

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Food"
            else -> "Drink"
        }
    }
}