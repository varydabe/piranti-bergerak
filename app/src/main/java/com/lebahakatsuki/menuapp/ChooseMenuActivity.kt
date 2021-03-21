package com.lebahakatsuki.menuapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.lebahakatsuki.menuapp.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_choose_menu.*

class ChooseMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_menu)

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        tabs.setupWithViewPager(viewPager)

        btnCancel.setOnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            it.context.startActivity(intent)
        }

        btnOrder.setOnClickListener {
            finish()
        }
    }
    
    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "Pilih menu kesukaanmu", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()

    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "Order diproses", Toast.LENGTH_SHORT).show()
    }
}