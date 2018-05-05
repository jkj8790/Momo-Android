package com.sorrowbeaver.momo.map.create

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.sorrowbeaver.momo.domain.interactor.CreateMap
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.fake.FakeGetMe
import com.sorrowbeaver.momo.scheduler.TrampolineSchedulerProvider
import io.reactivex.Observable
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

  inner class SuccessCreateMap : CreateMap(mock()) {
    override fun execute(params: Params): Observable<MomoMap> {
      return Observable.just(fakeMap)
    }
  }

  private fun createPresenter(createMap: CreateMap) =
    CreateMapPresenter(
      mockView, TrampolineSchedulerProvider,
      createMap, FakeGetMe
    )
}
