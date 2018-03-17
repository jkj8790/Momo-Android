package com.sorrowbeaver.momo

import android.app.Application
import com.sorrowbeaver.momo.data.di.DaggerDataComponent
import com.sorrowbeaver.momo.di.ApplicationComponent
import com.sorrowbeaver.momo.di.ApplicationModule
import com.sorrowbeaver.momo.di.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary

class MomoApplication : Application() {

  lateinit var component: ApplicationComponent
  private set

  override fun onCreate() {
    super.onCreate()
    if (LeakCanary.isInAnalyzerProcess(this)) {
      return
    }
    LeakCanary.install(this)

    val dataComponent = DaggerDataComponent.create()

    component = DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))
        .dataComponent(dataComponent)
        .build()
  }
}

