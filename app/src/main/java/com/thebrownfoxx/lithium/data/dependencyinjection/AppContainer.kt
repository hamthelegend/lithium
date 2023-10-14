package com.thebrownfoxx.lithium.data.dependencyinjection

import android.content.Context
import com.thebrownfoxx.lithium.data.database.LithiumDatabase
import com.thebrownfoxx.lithium.data.repository.LithiumRepository
import com.thebrownfoxx.lithium.data.repository.OfflineLithiumRepository

interface AppContainer {
    val lithiumRepository: LithiumRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val lithiumRepository: LithiumRepository by lazy {
        OfflineLithiumRepository(LithiumDatabase.getDatabase(context).expressionDao())
    }
}