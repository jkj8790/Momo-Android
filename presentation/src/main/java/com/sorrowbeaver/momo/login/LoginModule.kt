package com.sorrowbeaver.momo.login

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [LoginModule.Binder::class])
class LoginModule(private val view: LoginContract.View) {

  @Provides
  fun provideLoginView() = view

  @Module
  interface Binder {
    @Binds
    fun loginPresenter(loginPresenter: LoginPresenter):
      LoginContract.Presenter
  }
}
