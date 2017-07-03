package com.koroliov.rightly.ui.location

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.koroliov.rightly.R
import com.koroliov.rightly.ui.MainActivity

/**
 * Created by dima_korolev on 04/07/2017.
 */

class LocationMainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_location_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.setActionClickListener {

        }
    }
}