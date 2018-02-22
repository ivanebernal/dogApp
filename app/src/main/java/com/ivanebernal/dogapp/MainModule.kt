package com.ivanebernal.dogapp

import dagger.Module
import dagger.Provides

/**
 * Created by iesparza on 2/22/18.
 */
@Module
class MainModule(val dogViewModel: DogViewModel) {

    @Provides fun dogInteractor(dogService: DogService): DogInteractor = DogInteractor(dogService)

    @Provides fun dogPresenter(dogInteractor: DogInteractor): DogPresenter =
            DogPresenter(dogViewModel, dogInteractor)
}