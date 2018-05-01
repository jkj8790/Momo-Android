package com.sorrowbeaver.momo.map.create

import com.sorrowbeaver.momo.domain.interactor.CreateMap
import com.sorrowbeaver.momo.domain.interactor.GetMe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
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

    val me = getMe.execute(Unit).blockingFirst()
    val params = CreateMap.Params(
      name, description,
      private, me.id, me.userName
    )

    createMap.execute(params)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeBy(onNext = {
        view.showSuccessToast()
        view.close()
      }, onError = {
        it.printStackTrace()
      })
  }
}