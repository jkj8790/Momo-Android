package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
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
class GetPinsTest {

  @Mock lateinit var pinRepository: PinRepository
  lateinit var getPins: GetPins

  @Before fun setUp() {
    `when`(pinRepository.pins()).thenReturn(Observable.empty())
    getPins = GetPins(pinRepository, TestScheduler(), TestScheduler())
  }

  @Test fun testCreatePin() {
    getPins.buildObservable(Unit)

    verify(pinRepository).pins()
  }

}
