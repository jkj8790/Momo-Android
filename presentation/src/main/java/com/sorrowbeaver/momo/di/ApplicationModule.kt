package com.sorrowbeaver.momo.di

import android.app.Application
import com.sorrowbeaver.momo.login.LoginComponent
import com.sorrowbeaver.momo.main.MainComponent
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
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
  @Named("executor")
  fun provideExecutorScheduler() = Schedulers.io()

  @Provides
  @Singleton
  @Named("postExecution")
  fun providePostExecutionScheduler() = AndroidSchedulers.mainThread()!!
}
