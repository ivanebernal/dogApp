package com.ivanebernal.dogapp

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by iesparza on 2/22/18.
 */
@Module
class NetworkModule {
    @Provides fun retrofit(): Retrofit =
        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://dog.ceo/api/")
                .build()

    @Provides fun dogService(retrofit: Retrofit): DogService =
            retrofit.create(DogService::class.java)
}