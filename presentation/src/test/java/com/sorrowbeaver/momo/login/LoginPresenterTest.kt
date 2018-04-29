package com.sorrowbeaver.momo.login

import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.Login
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import com.sorrowbeaver.momo.model.UserModel
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class LoginPresenterTest {

  private lateinit var loginPresenter: LoginPresenter

  @Mock
  private val mockMapper = mock<UserModelDataMapper>()
  @Mock
  private val mockView = mock<LoginContract.View>()
  @Mock
  private val mockUser = mock<User>()
  @Mock
  private val mockUserModel = mock<UserModel>()

  private val fakeId = "id"
  private val fakePassword = "password"

  @Before
  fun setUp() {
    RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
    RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
    // TODO 왜 setInitComputation 하면 제대로 동작하지 않는지 조사
    `when`(mockMapper.transform(mockUser)).thenReturn(mockUserModel)
  }

  @Test
  fun testLogin() {
    loginPresenter = createPresenter(SuccessLogin(mockUser))

    loginPresenter.login(fakeId, fakePassword)

    verify(mockView).showLoading()
    verify(mockView).onSuccessLogin(anyOrNull())
    verify(mockView).hideLoading()
  }

  @Test
  fun testError() {
    loginPresenter = createPresenter(FailLogin())

    loginPresenter.login(fakeId, fakePassword)

    verify(mockView).showLoading()
    verify(mockView).onLoginError()
    verify(mockView).hideLoading()
  }

  class SuccessLogin(private val expectedUser: User) : Login(
    mock(),
    Schedulers.trampoline(), Schedulers.trampoline()
  ) {

    override fun buildObservable(params: Params): Observable<User> {
      return Observable.just(expectedUser)
    }
  }

  class FailLogin : Login(
    mock(),
    Schedulers.trampoline(), Schedulers.trampoline()
  ) {

    override fun buildObservable(params: Params): Observable<User> {
      return Observable.error(RuntimeException())
    }
  }

  private fun createPresenter(login: Login) =
    LoginPresenter(mockMapper, login).apply { takeView(mockView) }
}
