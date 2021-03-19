package com.lebahakatsuki.menuapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lebahakatsuki.menuapp.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_choose_menu.*

class ChooseMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_menu)

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        tabs.setupWithViewPager(viewPager)

        btnOrder.setOnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            it.context.startActivity(intent)
        }
    }
}