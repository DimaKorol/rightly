package com.koroliov.rightly.ui.location.add

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.koroliov.rightly.R
import kotlinx.android.synthetic.main.fragment_choose_location.*
import android.content.Context.LOCATION_SERVICE
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import com.google.android.gms.maps.*
import android.location.LocationListener
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.OnSuccessListener


/**
 * Created by dima_korolev on 06/07/2017.
 */

class ChooseLocationFragment : Fragment(), OnMapReadyCallback {

    var mapView: GoogleMap? = null
    var marker: Marker? = null
    var circle: Circle? = null

    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            result?.locations?.get(0)?.let {
                updateUI(it)
            }
        }
    }

    private val mLocationRequest: LocationRequest
        get() {
            val mLocationRequest = LocationRequest()
            mLocationRequest.interval = 1
            mLocationRequest.fastestInterval = 1
            mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            return mLocationRequest
        }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_choose_location, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onStop() {
        super.onStop()
        mFusedLocationClient?.removeLocationUpdates(mLocationCallback)
    }

    @SuppressLint("MissingPermission")
    override fun onStart() {
        super.onStart()
        mFusedLocationClient?.requestLocationUpdates(mLocationRequest,
                mLocationCallback,
                null)
    }



    @SuppressLint("MissingPermission")
    private fun defineLocation() {

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)

        mFusedLocationClient?.requestLocationUpdates(mLocationRequest,
                mLocationCallback,
                null)
}

    fun updateUI(location: Location) {
        var position = location.getLatLng()

        mapView?.moveCamera(CameraUpdateFactory.newLatLng(position))

        mapView?.animateCamera(CameraUpdateFactory.zoomTo(18f))

        marker?.remove()
        marker = mapView?.addMarker(MarkerOptions().position(position))

        circle?.remove()
        circle = mapView?.addCircle(CircleOptions().center(position).radius(location.accuracy.toDouble()).strokeColor(Color.RED))
    }

    override fun onMapReady(mapView: GoogleMap?) {
        this.mapView = mapView
        defineLocation()
    }
}

fun Location.getLatLng(): LatLng {
    return LatLng(latitude, longitude)
}