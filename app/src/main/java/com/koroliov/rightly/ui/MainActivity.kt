package com.koroliov.rightly.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.AdapterViewAnimator
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

    fun setActionClickListener(callback: (() -> Unit)?) {
        if (callback == null) {
            actionBtn.setOnClickListener(null)
            if (actionBtn.visibility == View.VISIBLE) {
                actionBtn.animate()
                        .translationXBy(actionBtn.width.toFloat())
                        .translationYBy(-actionBtn.height.toFloat())
                        .alpha(0f)
                        .setInterpolator(AccelerateInterpolator())
                        .setDuration(300)
                        .withEndAction { actionBtn.visibility = View.INVISIBLE }
                        .start()
            }
        } else {
            actionBtn.setOnClickListener { callback() }
            if (actionBtn.visibility == View.INVISIBLE) {
                actionBtn.animate()
                        .translationX(0f)
                        .translationY(0f)
                        .alpha(1f)
                        .setInterpolator(DecelerateInterpolator())
                        .setDuration(300)
                        .withStartAction { actionBtn.visibility = View.VISIBLE }
                        .start()
            }
        }
    }
}
