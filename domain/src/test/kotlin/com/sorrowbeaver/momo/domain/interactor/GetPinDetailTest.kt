package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.GetPinDetail.Params
import com.sorrowbeaver.momo.domain.repository.PinRepository
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPinDetailTest {

  @Mock lateinit var pinRepository: PinRepository
  lateinit var getPinDetail: GetPinDetail

  val FAKE_PIN_ID = 0L

  @Before fun setUp() {
    `when`(pinRepository.detail(FAKE_PIN_ID)).thenReturn(Observable.empty())
    getPinDetail = GetPinDetail(pinRepository, TestScheduler(), TestScheduler())
  }

  @Test fun testCreatePin() {
    getPinDetail.buildUseCaseObservable(Params(FAKE_PIN_ID))

    verify(pinRepository).detail(FAKE_PIN_ID)
  }

}
