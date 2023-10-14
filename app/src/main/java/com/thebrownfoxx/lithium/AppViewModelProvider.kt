package com.thebrownfoxx.lithium

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory

object AppViewModelProvider {
    val Factory = viewModelFactory {
    }
}

val CreationExtras.wardenApplication: LithiumApplication
    get() = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LithiumApplication)
