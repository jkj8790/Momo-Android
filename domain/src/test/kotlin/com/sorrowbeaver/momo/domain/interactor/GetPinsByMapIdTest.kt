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
class GetPinsByMapIdTest {

  @Mock
  private
  lateinit var pinRepository: PinRepository
  private lateinit var getPinsByMapId: GetPinsByMapId
  private val fakeMapID = 0L

  @Before
  fun setUp() {
    `when`(pinRepository.getPinsByMapId(fakeMapID)).thenReturn(Observable.empty())
    getPinsByMapId = GetPinsByMapId(pinRepository)
  }

  @Test
  fun testCreatePin() {
    getPinsByMapId.execute(GetPinsByMapId.Params(fakeMapID))

    verify(pinRepository).getPinsByMapId(fakeMapID)
  }
}
