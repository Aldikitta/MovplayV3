package com.example.movplayv3.data.initializer

import android.app.Application
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppInitializers @Inject constructor(
    private val initializers: Set<@JvmSuppressWildcards AppInitializer>
) {
    fun init(application: Application) {
        initializers.forEach { initializer ->
            initializer.init(application)
        }
    }
}