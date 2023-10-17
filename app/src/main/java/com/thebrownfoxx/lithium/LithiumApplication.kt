package com.thebrownfoxx.lithium

import android.app.Application
import com.thebrownfoxx.lithium.data.dependencyinjection.AppContainer
import com.thebrownfoxx.lithium.data.dependencyinjection.AppDataContainer

class LithiumApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
