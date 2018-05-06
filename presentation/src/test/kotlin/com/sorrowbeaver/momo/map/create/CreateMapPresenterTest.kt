package com.sorrowbeaver.momo.map.create

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.sorrowbeaver.momo.domain.interactor.CreateMap
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.fake.FakeGetMe
import com.sorrowbeaver.momo.scheduler.TrampolineSchedulerProvider
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import java.util.Date

class CreateMapPresenterTest {

  private val mockView = mock<CreateMapContract.View>()
  private val fakeName = "name"
  private val fakeDescription = "description"
  private val fakeIsPrivate = true
  private val fakeMap = MomoMap(
    0L, "name", "description", true,
    1L, emptyList(), Date()
  )

  lateinit var defaultPrsenter: CreateMapPresenter

  @Before
  fun setUp() {
    defaultPrsenter = createPresenter(SuccessCreateMap())
  }

  @Test
  fun testSubscribeDoesNothing() {
    defaultPrsenter.subscribe()

    verifyZeroInteractions(mockView)
  }

  //TODO test unsubscribe

  @Test
  fun testCreateMapSuccess() {
    defaultPrsenter.createMap(fakeName, fakeDescription, fakeIsPrivate)

    verify(mockView).showLoading()
    verify(mockView).hideLoading()
    verify(mockView).showSuccessToast()
    verify(mockView).close()
  }

  @Test
  fun testCreateMapFail() {
    val presenter = CreateMapPresenter(
      mockView, TrampolineSchedulerProvider,
      FailCreateMap(), FakeGetMe
    )

    presenter.createMap(fakeName, fakeDescription, fakeIsPrivate)

    verify(mockView).showLoading()
    verify(mockView).hideLoading()
    verify(mockView).showError()
  }

  @Test
  fun testShowNameErrorWhenNameChangedToInvalid() {
    val invalidName = "A" // Because

    defaultPrsenter.onNameChanged(invalidName)

    verify(mockView).setNameLengthError(true)
    verify(mockView).setDoneButtonEnabled(false)
  }

  @Test
  fun testShowNameErrorWhenNameChangedToValid() {
    val validName = "IamGoodName" // Because

    defaultPrsenter.onNameChanged(validName)

    verify(mockView).setNameLengthError(false)
    verify(mockView).setDoneButtonEnabled(true)
  }

  inner class SuccessCreateMap : CreateMap(mock()) {
    override fun execute(params: Params): Observable<MomoMap> {
      return Observable.just(fakeMap)
    }
  }

  inner class FailCreateMap : CreateMap(mock()) {
    override fun execute(params: Params): Observable<MomoMap> {
      return Observable.error(RuntimeException("Error!"))
    }
  }

  private fun createPresenter(createMap: CreateMap) =
    CreateMapPresenter(
      mockView, TrampolineSchedulerProvider,
      createMap, FakeGetMe
    )
}
