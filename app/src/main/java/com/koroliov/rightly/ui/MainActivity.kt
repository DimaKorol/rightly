package com.koroliov.rightly.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.koroliov.rightly.R
import com.koroliov.rightly.R.id.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation.setOnNavigationItemSelectedListener {
            if (!it.isChecked) {
                when (it.itemId) {
                    homeSection -> true
                    locationSection -> true
                    timeSection -> true
                    profileSection -> true
                    else -> {
                        false
                    }
                }
            }
            true
        }
    }
}
