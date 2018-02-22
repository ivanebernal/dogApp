package com.ivanebernal.dogapp

import dagger.Component

/**
 * Created by iesparza on 2/22/18.
 */
@Component(
        dependencies = [DogAppComponent::class],
        modules = [MainModule::class]
)
interface MainComponent {
    fun inject(activity: MainActivity)
}