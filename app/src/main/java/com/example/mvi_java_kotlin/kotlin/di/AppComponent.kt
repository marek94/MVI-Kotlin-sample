package com.example.mvi_java_kotlin.kotlin.di

import com.example.mvi_java_kotlin.kotlin.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [ApiModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Factory {

        fun create(): AppComponent

        @BindsInstance
        fun baseUrl(@ApiURL apiURL: String): Factory
    }
}