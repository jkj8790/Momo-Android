package com.sorrowbeaver.momo.di

import com.sorrowbeaver.momo.data.di.DataComponent
import com.sorrowbeaver.momo.login.LoginFragment
import com.sorrowbeaver.momo.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, LoginModule::class],
    dependencies = [DataComponent::class])
interface ApplicationComponent {
  fun inject(loginFragment: LoginFragment)
  fun inject(mainActivity: MainActivity)
}
