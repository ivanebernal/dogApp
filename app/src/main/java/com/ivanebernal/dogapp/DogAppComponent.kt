package com.ivanebernal.dogapp

import dagger.Component

/**
 * Created by iesparza on 2/22/18.
 */
@Component(modules = [NetworkModule::class])
interface DogAppComponent {
    fun inject(app: DogApp)
    fun dogService(): DogService
}