package com.sorrowbeaver.momo.login

import dagger.Subcomponent

@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {

  fun inject(fragment: LoginFragment)

  @Subcomponent.Builder
  interface Builder {
    fun loginModule(loginModule: LoginModule): Builder
    fun build(): LoginComponent
  }
}
