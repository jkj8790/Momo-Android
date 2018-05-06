package com.sorrowbeaver.momo.login

import com.sorrowbeaver.momo.domain.interactor.Login
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import com.sorrowbeaver.momo.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class LoginPresenter @Inject constructor(
  private val view: LoginContract.View,
  private val schedulerProvider: SchedulerProvider,
  private val userModelDataMapper: UserModelDataMapper,
  private val login: Login
) : LoginContract.Presenter {
  private val disposables = CompositeDisposable()

  override fun subscribe() {
  }

  override fun unsubscribe() {
    disposables.clear()
  }

  override fun login(id: String, password: String) {
    view.showLoading()
    login.execute(Login.Params(id, password))
      .subscribeOn(schedulerProvider.io())
      .map(userModelDataMapper::transform)
      .observeOn(schedulerProvider.ui())
      .subscribeBy(
        onNext = { view.onSuccessLogin(it) },
        onError = {
          view.onLoginError()
          view.hideLoading()
        },
        onComplete = { view.hideLoading() }
      ).let(disposables::add)
  }
}
