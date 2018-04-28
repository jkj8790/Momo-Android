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

  @Mock
  private lateinit var pinRepository: PinRepository
  private lateinit var createPin: CreatePin

  private val testPinName = "fake"
  private val testPinType = PinType.SHOP
  private val testAuthorName = "author"
  private val testAuthorID = 0L
  private val testMapId = 1L

  @Before fun setUp() {
    `when`(pinRepository.createPin(
        testPinName,
        testPinType,
        testAuthorID,
        testAuthorName,
        testMapId
    )).thenReturn(Observable.empty())
    createPin = CreatePin(pinRepository, TestScheduler(), TestScheduler())
  }

  @Test fun testCreatePin() {
    createPin.buildObservable(Params(
        testPinName,
        testPinType,
        testAuthorID,
        testAuthorName,
        testMapId
    ))

    verify(pinRepository).createPin(
        testPinName,
        testPinType,
        testAuthorID,
        testAuthorName,
        testMapId
    )
  }

}
