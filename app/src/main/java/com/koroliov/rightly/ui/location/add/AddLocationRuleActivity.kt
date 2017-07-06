package com.koroliov.rightly.ui.location.add

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.koroliov.rightly.R

/**
 * Created by dima_korolev on 06/07/2017.
 */

class AddLocationRuleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location_rule)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ChooseLocationFragment())
                .addToBackStack(null)
                .commit()
    }
}