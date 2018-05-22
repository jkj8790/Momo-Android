package com.sorrowbeaver.momo.main

import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.model.MomoMapModel
import com.sorrowbeaver.momo.model.UserModel
import com.sorrowbeaver.momo.scheduler.TrampolineSchedulerProvider
import com.sorrowbeaver.momo.stub.mapper.MapModelMapperStub
import com.sorrowbeaver.momo.stub.mapper.UserModelMapperStub
import com.sorrowbeaver.momo.stub.usecase.GetMapsByUserIdStub
import com.sorrowbeaver.momo.stub.usecase.GetMeFailStub
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import java.util.Date

class MainPresenterTest {

  @Mock
  private val mockView = mock<MainContract.View>()

  private val fakeUserModel = UserModel(
    1L, "I am fake user", "fake://profile.url"
  )

  private val fakeMapModel = MomoMapModel(
    0L, "Fake map", "I am fake map", false,
    1L, emptyList(), Date()
  )

  private val userModelMapperStub = UserModelMapperStub(fakeUserModel)
  private val mapModelMapperStub = MapModelMapperStub(fakeMapModel)

  @Before
  fun setUp() {
  }

  @Test
  fun testGetMe() {
    val noProfileUser = UserModel(
      1L, "I have no profile :(", null
    )
    val mainPresenter = createPresenter(expectedUserModel = noProfileUser)

    mainPresenter.loadMe()

    verify(mockView).showLoading()
    verify(mockView).showUserName(anyOrNull())
    verify(mockView).showMaps(anyOrNull())
    verify(mockView).hideLoading()
  }

  @Test
  fun testGetMeWithProfile() {
    val mainPresenter = createPresenter()

    mainPresenter.loadMe()

    verify(mockView).showLoading()
    verify(mockView).showUserName(anyOrNull())
    verify(mockView).showProfileImage(anyOrNull())
    verify(mockView).showMaps(anyOrNull())
    verify(mockView).hideLoading()
  }

  @Test
  fun testFailedGetMeShowError() {
    val mainPresenter = createPresenter()

    mainPresenter.loadMe()

    verify(mockView).showLoading()
    verify(mockView).showError()
    verify(mockView).hideLoading()
  }

  private fun createPresenter(
    expectedUserModel: UserModel = fakeUserModel,
    expectedMapModel: MomoMapModel = fakeMapModel
  ) =
    MainPresenter(
      mockView,
      TrampolineSchedulerProvider,
      GetMeFailStub,
      GetMapsByUserIdStub,
      UserModelMapperStub(expectedUserModel),
      MapModelMapperStub(expectedMapModel)
    )
}
