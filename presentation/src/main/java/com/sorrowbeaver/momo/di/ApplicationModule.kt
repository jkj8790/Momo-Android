package com.sorrowbeaver.momo.di

import android.app.Application
import com.sorrowbeaver.momo.data.entity.mapper.UserEntityDataMapper
import com.sorrowbeaver.momo.data.repository.datasource.user.UserDataRepository
import com.sorrowbeaver.momo.data.repository.datasource.user.UserDataStoreFactory
import com.sorrowbeaver.momo.domain.repository.UserRepository
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

  @Provides @Singleton
  fun provideApplicationContext() = application

  @Provides @Singleton
  fun provideUserDataStoreFactory() = UserDataStoreFactory()

  @Provides @Singleton
  fun provideUserModelDataMapper() = UserModelDataMapper()

  @Provides @Singleton
  fun provideUserEntityDataMapper() = UserEntityDataMapper()

  @Provides @Singleton
  fun provideUserRepository(userDataRepository: UserDataRepository): UserRepository
      = userDataRepository

  @Provides @Singleton
  @Named("executor")
  fun provideExecutorScheduler() = Schedulers.io()

  @Provides @Singleton
  @Named("postExecution")
  fun providePostExecutionScheduler() = AndroidSchedulers.mainThread()!!

}
