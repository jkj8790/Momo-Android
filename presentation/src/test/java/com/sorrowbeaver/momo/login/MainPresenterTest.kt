package com.sorrowbeaver.momo.login

import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.GetMe
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.main.MainContract
import com.sorrowbeaver.momo.main.MainPresenter
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

class MainPresenterTest {

  private lateinit var mainPresenter: MainPresenter
  @Mock private val mockView = mock<MainContract.View>()
  @Mock private val mockMapper = mock<UserModelDataMapper>()
  @Mock private val mockUser = mock<User>()
  @Mock private val mockUserModel = mock<UserModel>()

  @Before
  fun setUp() {
    RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
    `when`(mockMapper.transform(mockUser)).thenReturn(mockUserModel)
  }

  @Test
  fun testGetMe() {
    mainPresenter = MainPresenter(SuccessGetMe(mockUser), mockMapper, mockView)

    mainPresenter.loadMe()

    verify(mockView).showLoading()
    verify(mockView).showUserName(anyOrNull())
    verify(mockView).showProfileImage(anyOrNull())
    verify(mockView).hideLoading()
  }

  class SuccessGetMe(private val expectedUser: User) : GetMe(mock(),
      Schedulers.trampoline(), Schedulers.trampoline()
  ) {

    override fun buildObservable(params: Unit): Observable<User> {
      return Observable.just(expectedUser)
    }
  }


}
