package com.koroliov.rightly

import android.app.Application
import android.content.Context

/**
 * Created by dima_korolev on 03/07/2017.
 */

class RightlyApplication : Application() {

    companion object Instance {
        lateinit var instance: RightlyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        RightlyApplication.instance = this
    }
}

val appContext: Context by lazy { RightlyApplication.instance }
