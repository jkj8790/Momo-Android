package com.sorrowbeaver.momo.main

import com.sorrowbeaver.momo.domain.interactor.GetMapsByUserId
import com.sorrowbeaver.momo.domain.interactor.GetMe
import com.sorrowbeaver.momo.mapper.MomoMapModelDataMapper
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import com.sorrowbeaver.momo.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MainPresenter @Inject constructor(
  private val view: MainContract.View,
  private val schedulerProvider: SchedulerProvider,
  private val getMe: GetMe,
  private val getMapsByUserId: GetMapsByUserId,
  private val userModelMapper: UserModelDataMapper,
  private val mapModelMapper: MomoMapModelDataMapper
) : MainContract.Presenter {
  private val disposables = CompositeDisposable()

  override fun subscribe() {
    loadMe()
  }

  override fun unsubscribe() {
    disposables.clear()
  }

  override fun loadMe() {
    view.showLoading()
    getMe.execute(Unit)
      .subscribeOn(schedulerProvider.io())
      .map(userModelMapper::transform)
      .flatMap { userModel ->
        getMapsByUserId.execute(GetMapsByUserId.Params(userModel.id))
          .map(mapModelMapper::transform)
          .map { userModel to it }
      }
      .observeOn(schedulerProvider.ui())
      .subscribeBy(
        onNext = { pair ->
          val userModel = pair.first
          val mapModels = pair.second
          userModel.profileUrl?.let {
            view.showProfileImage(it)
          }
          view.showUserName(userModel.userName)
          view.showMaps(mapModels)
        },
        onError = {
          it.printStackTrace()
          view.showError()
          view.hideLoading()
        },
        onComplete = {
          view.hideLoading()
        }
      ).let(disposables::add)
  }
}
