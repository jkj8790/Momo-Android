package com.sorrowbeaver.momo.main

import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.GetMe
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import com.sorrowbeaver.momo.model.UserModel
import com.sorrowbeaver.momo.scheduler.TrampolineSchedulerProvider
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class MainPresenterTest {

  @Mock
  private val mockView = mock<MainContract.View>()
  @Mock
  private val mockMapper = mock<UserModelDataMapper>()
  @Mock
  private val mockUser = mock<User>()
  @Mock
  private val mockUserModel = mock<UserModel>()

  @Before
  fun setUp() {
    RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
    RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    `when`(mockMapper.transform(mockUser)).thenReturn(mockUserModel)
  }

  @Test
  fun testGetMe() {
    val mainPresenter = createPresenter(SuccessGetMe(mockUser))

    mainPresenter.loadMe()

    verify(mockView).showLoading()
    verify(mockView).showUserName(anyOrNull())
    verify(mockView).hideLoading()
  }

  @Test
  fun testGetMeWithProfile() {
    val mainPresenter = createPresenter(SuccessGetMe(mockUser))
    `when`(mockUserModel.profileUrl).thenReturn("profile")

    mainPresenter.loadMe()

    verify(mockView).showLoading()
    verify(mockView).showUserName(anyOrNull())
    verify(mockView).showProfileImage(anyOrNull())
    verify(mockView).hideLoading()
  }

  @Test
  fun testFailedGetMeShowError() {
    val mainPresenter = createPresenter(FailGetMe())

    mainPresenter.loadMe()

    verify(mockView).showLoading()
    verify(mockView).showError()
    verify(mockView).hideLoading()
  }

  class SuccessGetMe(private val expectedUser: User) : GetMe(mock()) {

    override fun execute(params: Unit): Observable<User> {
      return Observable.just(expectedUser)
    }
  }

  class FailGetMe : GetMe(mock()) {

    override fun execute(params: Unit): Observable<User> {
      return Observable.error(RuntimeException())
    }
  }

  private fun createPresenter(getMe: GetMe) =
    MainPresenter(mockView, TrampolineSchedulerProvider, getMe, mockMapper)
}
