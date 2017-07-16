package com.sorrowbeaver.momo.login

import com.sorrowbeaver.momo.domain.interactor.Login
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import io.reactivex.observers.DisposableObserver

class LoginPresenter(
    val view: LoginContract.View,
    val userModelDataMapper: UserModelDataMapper,
    val login: Login // TODO consider using Dagger if usecases are more added
) : LoginContract.Presenter {

  override fun start() {
  }

  override fun login(id: String, password: String) {
    view.showLoading()
    login.execute(
        object : DisposableObserver<User>() {
          override fun onNext(user: User) {
            view.onSuccessLogin(userModelDataMapper.transform(user))
            view.hideLoading()
          }

          override fun onError(e: Throwable?) {
            view.onLoginError()
            view.hideLoading()
          }

          override fun onComplete() {

          }
        },
        Login.Params(id, password)
    )
  }
}
