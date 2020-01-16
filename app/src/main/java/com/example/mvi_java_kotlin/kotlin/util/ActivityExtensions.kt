package com.example.mvi_java_kotlin.kotlin.util

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified VM : ViewModel> AppCompatActivity.provideViewModel(crossinline createVM: () -> VM): VM =
    ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = createVM() as T
    }).get(VM::class.java)