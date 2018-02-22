package com.ivanebernal.dogapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MainActivity : AppCompatActivity(), DogViewModel {
    private val DOG_URL = "DOG_URL"

    private val component: MainComponent by lazy {
        DaggerMainComponent.builder()
                .mainModule(MainModule(this))
                .dogAppComponent(DogApp.getApp(this).component)
                .build()
    }

    @Inject lateinit var dogPresenter: DogPresenter

    @BindView(R.id.dog_imageview)
    lateinit var dogImageView: ImageView

    private var currentDogUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)
        component.inject(this)

        if (savedInstanceState == null) dogPresenter.requestAnotherDog()
        else {
            val url = savedInstanceState.getString(DOG_URL, "")
            currentDogUrl = url
            changeDogImage(url)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(DOG_URL, currentDogUrl)
        super.onSaveInstanceState(outState)
    }

    @OnClick(R.id.another_dog_button)
    fun onAnotherDogClicked() {
        dogPresenter.requestAnotherDog()
    }

    override fun changeDogImage(url: String) {
        currentDogUrl = url
        Picasso.with(this).load(url).into(dogImageView)
    }

    override fun showDogError() {
        Picasso.with(this).load(R.drawable.dogerror).into(dogImageView)
        Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_LONG).show()
    }
}
