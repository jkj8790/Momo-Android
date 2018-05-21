package com.sorrowbeaver.momo.main

import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.GetMapsByUserId
import com.sorrowbeaver.momo.domain.interactor.GetMe
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.mapper.MomoMapModelDataMapper
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import com.sorrowbeaver.momo.model.MomoMapModel
import com.sorrowbeaver.momo.model.UserModel
import com.sorrowbeaver.momo.scheduler.TrampolineSchedulerProvider
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class MainPresenterTest {

  @Mock
  private val mockView = mock<MainContract.View>()
  @Mock
  private val mockUserMapper = mock<UserModelDataMapper>()
  @Mock
  private val mockMapMapper = mock<MomoMapModelDataMapper>()
  @Mock
  private val mockUser = mock<User>()
  @Mock
  private val mockUserModel = mock<UserModel>()
  @Mock
  private val mockMap = mock<MomoMap>()
  @Mock
  private val mockMapModel = mock<MomoMapModel>()

  @Before
  fun setUp() {
    `when`(mockUserMapper.transform(mockUser)).thenReturn(mockUserModel)
    `when`(mockMapMapper.transform(mockMap)).thenReturn(mockMapModel)
  }

  @Test
  fun testGetMe() {
    val mainPresenter = createPresenter(
      SuccessGetMe(mockUser),
      SuccessGetMapsByUserId(mockMap)
    )

    mainPresenter.loadMe()

    verify(mockView).showLoading()
    verify(mockView).showUserName(anyOrNull())
    verify(mockView).showMaps(anyOrNull())
    verify(mockView).hideLoading()
  }

  @Test
  fun testGetMeWithProfile() {
    val mainPresenter = createPresenter(
      SuccessGetMe(mockUser),
      SuccessGetMapsByUserId(mockMap)
    )

    `when`(mockUserModel.profileUrl).thenReturn("profile")

    mainPresenter.loadMe()

    verify(mockView).showLoading()
    verify(mockView).showUserName(anyOrNull())
    verify(mockView).showProfileImage(anyOrNull())
    verify(mockView).showMaps(anyOrNull())
    verify(mockView).hideLoading()
  }

  @Test
  fun testFailedGetMeShowError() {
    val mainPresenter = createPresenter(FailGetMe(), SuccessGetMapsByUserId(mockMap))

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

  class SuccessGetMapsByUserId(
    private val expected: MomoMap
  ) : GetMapsByUserId(mock()) {

    override fun execute(params: Params): Observable<List<MomoMap>> {
      return Observable.just(listOf(expected))
    }
  }

  private fun createPresenter(getMe: GetMe, getMapsByUserId: GetMapsByUserId) =
    MainPresenter(
      mockView,
      TrampolineSchedulerProvider,
      getMe,
      getMapsByUserId,
      mockUserMapper,
      mockMapMapper
    )
}
