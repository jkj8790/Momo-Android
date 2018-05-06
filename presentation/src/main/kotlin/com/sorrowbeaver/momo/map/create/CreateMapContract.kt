package com.sorrowbeaver.momo.map.create

import com.sorrowbeaver.momo.BasePresenter
import com.sorrowbeaver.momo.BaseView

interface CreateMapContract {

  interface Presenter : BasePresenter {
    fun createMap(name: String, description: String, private: Boolean)
    fun onNameChanged(name: String)
  }

  interface View : BaseView<Presenter> {
    fun showLoading()
    fun showSuccessToast()
    fun close()
    fun setNameLengthError(error: Boolean)
    fun setDoneButtonEnabled(enabled: Boolean)
  }
}
