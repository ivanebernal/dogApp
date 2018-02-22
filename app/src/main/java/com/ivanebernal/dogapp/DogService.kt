package com.ivanebernal.dogapp

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by iesparza on 2/21/18.
 */
interface DogService {
    @GET("breeds/image/random")
    fun getRandomDogImage(): Call<DogImage>
}