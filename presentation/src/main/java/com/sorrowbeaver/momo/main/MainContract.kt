package com.sorrowbeaver.momo.main

import com.sorrowbeaver.momo.BasePresenter
import com.sorrowbeaver.momo.BaseView

interface MainContract {

  interface View : BaseView<Presenter> {
    fun showLoading()
    fun hideLoading()
    fun showError()
    fun showProfileImage(profileUrl: String)
    fun showUserName(userName: String)
  }

  interface Presenter : BasePresenter<View> {
    fun loadMe()
  }
}
