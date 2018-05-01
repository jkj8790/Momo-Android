package com.sorrowbeaver.momo.map.create

import com.sorrowbeaver.momo.domain.interactor.CreateMap
import com.sorrowbeaver.momo.domain.interactor.GetMe
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class CreateMapPresenter @Inject constructor(
  private val view: CreateMapContract.View,
  private val createMap: CreateMap,
  private val getMe: GetMe
) : CreateMapContract.Presenter {
  override fun subscribe() {
  }

  override fun unsubscribe() {
  }

  override fun createMap(name: String, description: String, private: Boolean) {
    view.showLoading()

    val me = getMe.buildObservable(Unit).blockingFirst()
    val params = CreateMap.Params(
      name, description,
      private, me.id, me.userName
    )

    createMap.buildObservable(params)
      .subscribeBy(onNext = {
        view.showSuccessToast()
        view.close()
      }, onError = {
        it.printStackTrace()
      })
  }
}