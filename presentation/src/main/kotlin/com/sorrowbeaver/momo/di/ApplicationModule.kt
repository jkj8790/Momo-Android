package com.sorrowbeaver.momo.di

import android.app.Application
import com.sorrowbeaver.momo.login.LoginComponent
import com.sorrowbeaver.momo.main.MainComponent
import com.sorrowbeaver.momo.mapper.MomoMapModelDataMapper
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import com.sorrowbeaver.momo.scheduler.DefaultSchedulerProvider
import com.sorrowbeaver.momo.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [LoginComponent::class, MainComponent::class])
class ApplicationModule(private val application: Application) {

  @Provides
  @Singleton
  fun provideApplicationContext() = application

  @Provides
  @Singleton
  fun provideUserModelDataMapper() = UserModelDataMapper()

  @Provides
  @Singleton
  fun provideMapModelDataMapper() = MomoMapModelDataMapper()

  @Provides
  @Singleton
  fun provideSchedulerProvider(): SchedulerProvider = DefaultSchedulerProvider
}
