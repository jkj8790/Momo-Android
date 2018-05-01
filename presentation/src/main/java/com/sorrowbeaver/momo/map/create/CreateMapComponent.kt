package com.sorrowbeaver.momo.map.create

import dagger.Subcomponent

@Subcomponent(modules = [CreateMapModule::class])
interface CreateMapComponent {

  fun inject(activity: CreateMapActivity)

  @Subcomponent.Builder
  interface Builder {
    fun createMapModule(createMapModule: CreateMapModule): Builder
    fun build(): CreateMapComponent
  }
}