package com.sorrowbeaver.momo.main

import com.sorrowbeaver.momo.domain.interactor.GetProfile
import com.sorrowbeaver.momo.domain.interactor.GetProfile.Params
import com.sorrowbeaver.momo.domain.repository.UserRepository
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainPresenter(
    userRepository: UserRepository,
    val view: MainContract.View
): MainContract.Presenter {

  private val getProfile = GetProfile(userRepository, Schedulers.io(), AndroidSchedulers.mainThread())
  private val userModelMapper = UserModelDataMapper()
  private val disposables = CompositeDisposable()

  override fun subscribe() {
    loadMe()
  }

  override fun unsubscribe() {
    disposables.clear()
  }

  override fun loadMe() {
    view.showLoading()
     getProfile.get(Params(0))
        .observeOn(Schedulers.computation())
        .map(userModelMapper::transform)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeBy(
            onNext = { userModel ->
              userModel.profileUrl?.let(view::showProfileImage)
              view.showUserName(userModel.userName)
            },
            onError = {
              it.printStackTrace()
              view.showError()
              view.hideLoading()
            },
            onComplete = {
              view.hideLoading()
            }
        ) .let(disposables::add)
  }

}