package com.sorrowbeaver.momo.di

import com.sorrowbeaver.momo.login.LoginContract
import com.sorrowbeaver.momo.login.LoginPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class LoginModule {

  @Binds
  abstract fun loginPresenter(loginPresenter: LoginPresenter): LoginContract.Presenter
}
