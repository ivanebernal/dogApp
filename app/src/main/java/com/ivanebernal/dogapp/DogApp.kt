package com.ivanebernal.dogapp

import android.app.Application
import android.content.Context

/**
 * Created by iesparza on 2/22/18.
 */
class DogApp: Application() {

    val component: DogAppComponent by lazy {
        DaggerDogAppComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    companion object {
        fun getApp(context: Context): DogApp = context.applicationContext as DogApp
    }
}