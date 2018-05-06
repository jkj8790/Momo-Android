package com.sorrowbeaver.momo.map.create

import com.sorrowbeaver.momo.domain.interactor.CreateMap
import com.sorrowbeaver.momo.domain.interactor.GetMe
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.scheduler.SchedulerProvider
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class CreateMapPresenter @Inject constructor(
  private val view: CreateMapContract.View,
  private val schedulerProvider: SchedulerProvider,
  private val createMap: CreateMap,
  private val getMe: GetMe
) : CreateMapContract.Presenter {
  override fun subscribe() {
  }

  override fun unsubscribe() {
  }

  override fun createMap(name: String, description: String, private: Boolean) {
    view.showLoading()

    val me = getMe.execute(Unit).blockingFirst()
    val params = CreateMap.Params(
      name, description,
      private, me.id
    )

    createMap.execute(params)
      .subscribeOn(schedulerProvider.io())
      .observeOn(schedulerProvider.ui())
      .subscribeBy(onNext = {
        view.showSuccessToast()
        view.close()
      }, onError = {
        it.printStackTrace()
      })
  }

  override fun onNameChanged(name: String) {
    val valid = name.length in MomoMap.nameLengthRange
    view.setNameLengthError(!valid)
    view.setDoneButtonEnabled(valid)
  }
}
