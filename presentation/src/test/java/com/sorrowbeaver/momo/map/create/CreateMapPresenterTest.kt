package com.sorrowbeaver.momo.map.create

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.sorrowbeaver.momo.domain.interactor.CreateMap
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.fake.FakeGetMe
import com.sorrowbeaver.momo.rule.TrampolineSchedulerRule
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

import org.junit.Rule
import org.junit.Test
import java.util.Date

class CreateMapPresenterTest {

  @get:Rule
  private val rule = TrampolineSchedulerRule()
  private val mockView = mock<CreateMapContract.View>()

  private val fakeName = "name"
  private val fakeDescription = "description"
  private val fakeIsPrivate = true

  private val fakeMap = MomoMap(
    0L, "name", "description", true,
    1L, emptyList(), Date()
  )

  @Test
  fun testSubscribeDoesNothing() {
    val presenter = createPresenter(SuccessCreateMap())

    presenter.subscribe()

    verifyZeroInteractions(mockView)
  }

  @Test
  fun testCreateMapSuccess() {
    val presenter = createPresenter(SuccessCreateMap())

    presenter.createMap(fakeName, fakeDescription, fakeIsPrivate)

    verify(mockView).showLoading()
    verify(mockView).showSuccessToast()
    verify(mockView).close()
  }

  //TODO test unsubscribe

  inner class SuccessCreateMap : CreateMap(
    mock(), Schedulers.trampoline(), Schedulers.trampoline()
  ) {
    override fun buildObservable(params: Params): Observable<MomoMap> {
      return Observable.just(fakeMap)
    }
  }

  private fun createPresenter(createMap: CreateMap) =
    CreateMapPresenter(mockView, createMap, FakeGetMe)
}