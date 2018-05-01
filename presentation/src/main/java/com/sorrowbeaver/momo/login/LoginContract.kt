package com.sorrowbeaver.momo.login

import com.sorrowbeaver.momo.BasePresenter
import com.sorrowbeaver.momo.BaseView
import com.sorrowbeaver.momo.model.UserModel

interface LoginContract {
  interface View : BaseView<Presenter> {
    fun showLoading()
    fun hideLoading()
    fun onSuccessLogin(user: UserModel)
    fun onLoginError()
  }

  interface Presenter : BasePresenter {
    fun login(id: String, password: String)
  }
}
