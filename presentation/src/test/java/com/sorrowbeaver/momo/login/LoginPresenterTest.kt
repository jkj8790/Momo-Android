package com.sorrowbeaver.momo.login

import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.Login
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class LoginPresenterTest {

  lateinit var loginPresenter: LoginPresenter

  @Mock val mockMapper = mock<UserModelDataMapper>()
  @Mock val mockLogin = mock<Login>()
  @Mock val mockView = mock<LoginContract.View>()
  @Mock val mockUser = mock<User>()

  val FAKE_ID = "id"
  val FAKE_PWD = "password"

  @Before
  fun setUp() {
    loginPresenter = LoginPresenter(mockView, mockMapper, mockLogin)
  }

  @Test
  fun testLogin() {
    loginPresenter = LoginPresenter(mockView, mockMapper, SuccessLogin(mockUser))

    loginPresenter.login(FAKE_ID, FAKE_PWD)

    verify(mockView).showLoading()
    verify(mockView).onSuccessLogin(anyOrNull())
    verify(mockView).hideLoading()
  }

  @Test
  fun testError() {
    loginPresenter = LoginPresenter(mockView, mockMapper, FailLogin())

    loginPresenter.login(FAKE_ID, FAKE_PWD)

    verify(mockView).showLoading()
    verify(mockView).onLoginError()
    verify(mockView).hideLoading()
  }

  class SuccessLogin(val expectedUser: User) : Login(mock<UserRepository>(),
      Schedulers.trampoline(), Schedulers.trampoline()
  ) {

    override fun buildObservable(params: Params): Observable<User> {
      return Observable.just(expectedUser)
    }

    override fun execute(observer: DisposableObserver<User>, params: Params) {
      buildObservable(params).subscribe(observer)
    }
  }

  class FailLogin : Login(mock<UserRepository>(),
      Schedulers.trampoline(), Schedulers.trampoline()) {

    override fun buildObservable(params: Params): Observable<User> {
      return Observable.error(RuntimeException())
    }

    override fun execute(observer: DisposableObserver<User>, params: Params) {
      buildObservable(params).subscribe(observer)
    }
  }

}
