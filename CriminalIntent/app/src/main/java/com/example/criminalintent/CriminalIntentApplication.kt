package com.example.criminalintent

import android.app.Application

/**
 * This is called by the OS upon startup. This is a good place to do one time startup inits
 *
 * In order to the application class to be used by the system, we need to register it in the
 * manifest, which is done under the application tag, and we create a name property. That
 * way, the OS will create an instance of this class upon launch.
 */
class CriminalIntentApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // We override the oncreate method and create a repository instance as soon as the app is ready!
        // The parameter we pass is the context, which is just the current application context as recieved from the app
        CrimeRepository.initialize(this)
    }
}