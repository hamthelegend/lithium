package com.thebrownfoxx.lithium

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.thebrownfoxx.lithium.ui.screen.checkin.CheckInViewModel
import com.thebrownfoxx.lithium.ui.screen.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(lithiumApplication.container.lithiumRepository)
        }
        initializer {
            CheckInViewModel(
                lithiumRepository = lithiumApplication.container.lithiumRepository,
                application = lithiumApplication,
            )
        }
    }
}

val CreationExtras.lithiumApplication: LithiumApplication
    get() = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LithiumApplication)
