package com.sorrowbeaver.momo.di

import android.app.Application
import com.sorrowbeaver.momo.data.di.DataComponent
import com.sorrowbeaver.momo.login.LoginComponent
import com.sorrowbeaver.momo.main.MainComponent
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import dagger.Component
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
  modules = [ApplicationModule::class],
  dependencies = [DataComponent::class]
)
interface ApplicationComponent {

  fun application(): Application

  fun userModelDataMapper(): UserModelDataMapper

  @Named("executor")
  fun executorScheduler(): Scheduler

  @Named("postExecution")
  fun postExecutionScheduler(): Scheduler

  fun loginComponent(): LoginComponent.Builder

  fun mainComponent(): MainComponent.Builder

}
