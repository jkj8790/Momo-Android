package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.repository.PinRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPinsTest {

  @Mock
  private
  lateinit var pinRepository: PinRepository
  private lateinit var getPins: GetPins

  @Before
  fun setUp() {
    `when`(pinRepository.pins()).thenReturn(Observable.empty())
    getPins = GetPins(pinRepository)
  }

  @Test
  fun testCreatePin() {
    getPins.execute(Unit)

    verify(pinRepository).pins()
  }
}
