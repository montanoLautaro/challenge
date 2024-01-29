package com.example.eldarwallet.core


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.eldarwallet.R

class MyToolbar {
    fun show(activities: AppCompatActivity, title: String, upButton:Boolean) {
        activities.setSupportActionBar(activities.findViewById(R.id.toolbar))
        activities.supportActionBar?.title=title
        activities.supportActionBar?.setDisplayHomeAsUpEnabled(upButton)

        if (upButton) {
            activities.findViewById<Toolbar>(R.id.toolbar)?.setNavigationOnClickListener {
                activities.onBackPressed()
            }

        }
    }



}