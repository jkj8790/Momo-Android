package com.sorrowbeaver.momo.main

import com.sorrowbeaver.momo.BasePresenter
import com.sorrowbeaver.momo.BaseView
import com.sorrowbeaver.momo.model.LocationModel
import com.sorrowbeaver.momo.model.MomoMapModel

interface MainContract {

  interface View : BaseView<Presenter> {
    fun showLoading()
    fun hideLoading()
    fun showError()
    fun showProfileImage(profileUrl: String)
    fun showUserName(userName: String)
    fun showMaps(maps: List<MomoMapModel>)
    fun moveToCurrentLocation(location: LocationModel)
  }

  interface Presenter : BasePresenter {
    fun loadMe()
    fun loadCurrentLocation()
  }
}
