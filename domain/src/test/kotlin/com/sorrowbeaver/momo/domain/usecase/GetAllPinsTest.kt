package com.sorrowbeaver.momo.domain.usecase

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
class GetAllPinsTest {

  @Mock
  private
  lateinit var pinRepository: PinRepository
  private lateinit var getAllPins: GetAllPins

  @Before
  fun setUp() {
    `when`(pinRepository.getAllPins()).thenReturn(Observable.empty())
    getAllPins = GetAllPins(pinRepository)
  }

  @Test
  fun testCreatePin() {
    getAllPins.execute(Unit)

    verify(pinRepository).getAllPins()
  }
}
