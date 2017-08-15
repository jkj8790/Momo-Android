package com.sorrowbeaver.momo.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sorrowbeaver.momo.R
import com.sorrowbeaver.momo.data.repository.datasource.user.UserDataRepository
import com.sorrowbeaver.momo.data.repository.datasource.user.UserDataStoreFactory
import com.sorrowbeaver.momo.domain.interactor.Login
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.fragment

class LoginActivity : AppCompatActivity() {

  lateinit var loginPresenter: LoginPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    (fragment as? LoginFragment)?.let {
      loginPresenter = LoginPresenter(it, UserModelDataMapper(), Login(
          UserDataRepository(), Schedulers.io(), AndroidSchedulers.mainThread()
      ))

      it.presenter = loginPresenter
    }
  }

  private fun getUserDataRepository(): UserDataRepository {
    val factory = UserDataStoreFactory()
    return UserDataRepository()
  }
}
