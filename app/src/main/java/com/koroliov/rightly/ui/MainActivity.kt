package com.koroliov.rightly.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.koroliov.rightly.R
import com.koroliov.rightly.R.id.*
import com.koroliov.rightly.ui.feed.FeedFragment
import com.koroliov.rightly.ui.location.LocationMainFragment
import com.koroliov.rightly.ui.location.ProfileMainFragment
import com.koroliov.rightly.ui.location.TimeMainFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startFragment(FeedFragment())
        bottomNavigation.setOnNavigationItemSelectedListener {
            if (!it.isChecked) {
                when (it.itemId) {
                    homeSection -> startFragment(FeedFragment())
                    locationSection -> startFragment(LocationMainFragment())
                    timeSection -> startFragment(TimeMainFragment())
                    profileSection -> startFragment(ProfileMainFragment())
                    else -> false
                }
            }
            true
        }
    }

    fun startFragment(fragment: Fragment) {
        var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStackImmediate()
            fragmentTransaction.replace(R.id.fragmentContainer, fragment)
            fragmentTransaction.addToBackStack(null)
        } else {
            fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        }
        fragmentTransaction.commit()
    }
}
