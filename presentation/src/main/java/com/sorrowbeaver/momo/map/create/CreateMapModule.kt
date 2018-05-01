package com.sorrowbeaver.momo.map.create

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [CreateMapModule.Binder::class])
class CreateMapModule(private val view: CreateMapContract.View) {

  @Provides
  fun provideView() = view

  @Module
  interface Binder {
    @Binds
    fun createMapPresenter(presenter: CreateMapPresenter):
      CreateMapContract.Presenter
  }
}
