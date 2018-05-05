package com.sorrowbeaver.momo.map.create

import com.sorrowbeaver.momo.BasePresenter
import com.sorrowbeaver.momo.BaseView

interface CreateMapContract {

  interface Presenter : BasePresenter {
    fun createMap(name: String, description: String, private: Boolean)
  }

  interface View : BaseView<Presenter> {
    fun showLoading()
    fun showSuccessToast()
    fun close()
  }
}
