package com.ivanebernal.dogapp

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val DOG_URL = "DOG_URL"

    @BindView(R.id.dog_imageview) lateinit var dogImageView: ImageView

    private lateinit var dogService: DogService
    private var currentDogUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        createDogService()
        if (savedInstanceState == null) changeDogImage()
        else {
            val url = savedInstanceState.getString(DOG_URL, "")
            currentDogUrl = url
            setDogImage(url)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(DOG_URL, currentDogUrl)
        super.onSaveInstanceState(outState)

    }

    @OnClick(R.id.another_dog_button)
    fun onAnotherDogClicked() {
        changeDogImage()
    }

    private fun changeDogImage() {
        dogService.getRandomDogImage().enqueue(object : Callback<DogImage> {
            override fun onFailure(call: Call<DogImage>?, t: Throwable?) {
                setDogErrorImage()
                showErrorToast()
            }

            override fun onResponse(call: Call<DogImage>?, response: Response<DogImage>?) {
                val dogImage = response?.body()
                currentDogUrl = dogImage?.message
                if (dogImage?.status == "success") setDogImage(dogImage.message)
                else {
                    setDogErrorImage()
                    showErrorToast()
                }
            }
        })
    }

    private fun createDogService() {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://dog.ceo/api/")
                .build()
        dogService = retrofit.create(DogService::class.java)
    }

    private fun setDogImage(url: String) {
        Picasso.with(this).load(url).into(dogImageView)
    }

    private fun setDogErrorImage() {
        Picasso.with(this).load(R.drawable.dogerror).into(dogImageView)
    }

    private fun showErrorToast() {
        Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_LONG).show()
    }
}
