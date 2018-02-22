package com.ivanebernal.dogapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by iesparza on 2/22/18.
 */
class DogInteractor {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://dog.ceo/api/")
                .build()
    }

    private val dogService: DogService by lazy { retrofit.create(DogService::class.java) }

    fun getDogImage(callback: DogCallback){
        dogService.getRandomDogImage().enqueue(object: Callback<DogImage>{
            override fun onFailure(call: Call<DogImage>?, t: Throwable?) {
                callback.onDogError()
            }

            override fun onResponse(call: Call<DogImage>?, response: Response<DogImage>?) {
                val dogImage = response?.body()
                if (dogImage?.status != "success") callback.onDogError()
                else callback.onDogSuccess(dogImage)
            }
        } )
    }

}