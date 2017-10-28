package com.sorrowbeaver.momo.login

import com.sorrowbeaver.momo.domain.interactor.Login
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class LoginPresenter(
    val view: LoginContract.View,
    val userModelDataMapper: UserModelDataMapper,
    val login: Login // TODO consider using Dagger if usecases are more added
) : LoginContract.Presenter {
  private val disposables = CompositeDisposable()

  override fun subscribe() {
  }

  override fun unsubscribe() {
    disposables.clear()
  }

  override fun login(id: String, password: String) {
    view.showLoading()
    login.get(Login.Params(id, password))
        .observeOn(Schedulers.computation())
        .map { userModelDataMapper.transform(it) }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeBy (
            onNext = {
              view.onSuccessLogin(it)
            },
            onError = {
              it.printStackTrace()
              view.hideLoading()
            },
            onComplete = {
              view.hideLoading()
            }
        ).let { disposables.add(it) }
  }
}
