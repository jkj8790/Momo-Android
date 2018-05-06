package com.sorrowbeaver.momo.main

import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

  fun inject(mainActivity: MainActivity)

  @Subcomponent.Builder
  interface Builder {
    fun mainModule(module: MainModule): Builder
    fun build(): MainComponent
  }
}
