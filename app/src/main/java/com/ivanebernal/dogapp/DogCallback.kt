package com.ivanebernal.dogapp

/**
 * Created by iesparza on 2/22/18.
 */
interface DogCallback {
    fun onDogSuccess(dogImage: DogImage)
    fun onDogError()
}