package com.sorrowbeaver.momo

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class MomoApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    if (LeakCanary.isInAnalyzerProcess(this)) {
      return
    }
    LeakCanary.install(this)
  }
}

