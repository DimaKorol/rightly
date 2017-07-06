package com.koroliov.rightly.ui.location

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.koroliov.rightly.R
import com.koroliov.rightly.ui.MainActivity
import com.koroliov.rightly.ui.location.add.AddLocationRuleActivity
import com.strongpancakes.quest.utils.start

/**
 * Created by dima_korolev on 04/07/2017.
 */

class LocationMainFragment : Fragment() {
    val PERMISSIONS_REQUEST_LOCATION = 123

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_location_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setActionClickListener {
            if (requestPermission()) startAddActivity()
        }
    }

    private fun requestPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSIONS_REQUEST_LOCATION)
            return false
        }
        return true
    }

    private fun startAddActivity() {
        activity.start(AddLocationRuleActivity::class.java)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_LOCATION) {
           if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               startAddActivity()
           }
        }
    }
}