package com.ivanebernal.dogapp

/**
 * Created by iesparza on 2/22/18.
 */
class DogPresenter(private val dogViewModel: DogViewModel, private val dogInteractor: DogInteractor): DogCallback {

    fun requestAnotherDog(){ dogInteractor.getDogImage(this) }

    override fun onDogSuccess(dogImage: DogImage) { dogViewModel.changeDogImage(dogImage.message) }

    override fun onDogError() { dogViewModel.showDogError() }
}