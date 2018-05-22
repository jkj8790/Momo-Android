package com.sorrowbeaver.momo.di

import android.app.Application
import com.sorrowbeaver.momo.data.di.DataComponent
import com.sorrowbeaver.momo.login.LoginComponent
import com.sorrowbeaver.momo.main.MainComponent
import com.sorrowbeaver.momo.map.create.CreateMapComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  modules = [ApplicationModule::class],
  dependencies = [DataComponent::class]
)
interface ApplicationComponent {

  fun application(): Application

  fun loginComponent(): LoginComponent.Builder

  fun mainComponent(): MainComponent.Builder

  fun createMapComponent(): CreateMapComponent.Builder
}
