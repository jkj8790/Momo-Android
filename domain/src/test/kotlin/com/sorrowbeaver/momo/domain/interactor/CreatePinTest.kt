package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.CreatePin.Params
import com.sorrowbeaver.momo.domain.model.Pin.PinType
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
class CreatePinTest {

  @Mock lateinit var pinRepository: PinRepository
  lateinit var createPin: CreatePin

  val FAKE_PIN_NAME = "fake"
  val FAKE_PIN_TYPE = PinType.SHOP
  val FAKE_MAP_ID = 0L

  @Before fun setUp() {
    `when`(pinRepository.createPin(FAKE_PIN_NAME,
        FAKE_PIN_TYPE, FAKE_MAP_ID)).thenReturn(Observable.empty())
    createPin = CreatePin(pinRepository, TestScheduler(), TestScheduler())
  }

  @Test fun testCreatePin() {
    createPin.buildObservable(Params(FAKE_PIN_NAME, FAKE_PIN_TYPE, FAKE_MAP_ID))

    verify(pinRepository).createPin(
        FAKE_PIN_NAME, FAKE_PIN_TYPE, FAKE_MAP_ID
    )
  }

}
