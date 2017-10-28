package com.sorrowbeaver.momo.main

import com.sorrowbeaver.momo.domain.interactor.GetProfile
import com.sorrowbeaver.momo.domain.interactor.GetProfile.Params
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainPresenter(
    private val userRepository: UserRepository,
    val view: MainContract.View
): MainContract.Presenter {

  private val getProfile = GetProfile(userRepository, Schedulers.io(), AndroidSchedulers.mainThread())

  override fun loadMe() {
    view.showLoading()
    getProfile.execute(
        object : DisposableObserver<User>() {
          override fun onNext(user: User?) {
            val userModelMapper = UserModelDataMapper()
            user?.let {
              it.profileUrl?.let { view.showProfileImage(it) }
              view.showUserName(it.userName)
            }
          }

          override fun onError(e: Throwable?) {
            e?.printStackTrace()
          }

          override fun onComplete() {
            view.hideLoading()
          }

        }, Params(0)
    )
  }


  override fun start() {
    loadMe()
  }

}