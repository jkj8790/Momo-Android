package com.sorrowbeaver.momo.main

import dagger.Module
import dagger.Provides

@Module
class MainModule(private val view: MainContract.View) {

  @Provides
  fun provideMainView() = view
}
