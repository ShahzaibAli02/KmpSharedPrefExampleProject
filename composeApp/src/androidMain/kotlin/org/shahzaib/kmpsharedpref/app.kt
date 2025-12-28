package org.shahzaib.kmpsharedpref

import android.app.Application
import sharedpref.KMPSharedPref

class app : Application() {

    override fun onCreate()
    {
        super.onCreate()
        KMPSharedPref.init(this)
    }

}
