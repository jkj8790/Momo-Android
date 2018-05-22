package com.sorrowbeaver.momo.main

import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.GetMapsByUserId
import com.sorrowbeaver.momo.domain.interactor.GetMe
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.model.MomoMapModel
import com.sorrowbeaver.momo.model.UserModel
import com.sorrowbeaver.momo.scheduler.TrampolineSchedulerProvider
import com.sorrowbeaver.momo.stub.mapper.MapModelMapperStub
import com.sorrowbeaver.momo.stub.mapper.UserModelMapperStub
import com.sorrowbeaver.momo.stub.usecase.GetMapsByUserIdStub
import com.sorrowbeaver.momo.stub.usecase.GetMeFailStub
import com.sorrowbeaver.momo.stub.usecase.GetMeStub
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class MainPresenterTest {

  @Mock
  private val mockView = mock<MainContract.View>()
  @Mock
  private val mockUser = mock<User>()
  @Mock
  private val mockUserModel = mock<UserModel>()
  @Mock
  private val mockMap = mock<MomoMap>()
  @Mock
  private val mockMapModel = mock<MomoMapModel>()

  private val userModelMapper = UserModelMapperStub(mockUserModel)
  private val mapModelMapper = MapModelMapperStub(mockMapModel)

  @Before
  fun setUp() {
  }

  @Test
  fun testGetMe() {
    val mainPresenter = createPresenter(
      GetMeStub,
      GetMapsByUserIdStub(mockMap)
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
      GetMeStub,
      GetMapsByUserIdStub(mockMap)
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
    val mainPresenter = createPresenter(
      GetMeFailStub,
      GetMapsByUserIdStub(mockMap)
    )

    mainPresenter.loadMe()

    verify(mockView).showLoading()
    verify(mockView).showError()
    verify(mockView).hideLoading()
  }

  private fun createPresenter(getMe: GetMe, getMapsByUserId: GetMapsByUserId) =
    MainPresenter(
      mockView,
      TrampolineSchedulerProvider,
      getMe,
      getMapsByUserId,
      userModelMapper,
      mapModelMapper
    )
}
