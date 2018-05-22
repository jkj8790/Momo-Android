package com.sorrowbeaver.momo.domain.usecase

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.repository.PinRepository
import com.sorrowbeaver.momo.domain.usecase.GetPinDetail.Params
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPinDetailTest {

  @Mock
  private
  lateinit var pinRepository: PinRepository
  private lateinit var getPinDetail: GetPinDetail

  private val fakePinId = 0L

  @Before
  fun setUp() {
    `when`(pinRepository.detail(fakePinId)).thenReturn(Observable.empty())
    getPinDetail = GetPinDetail(pinRepository)
  }

  @Test
  fun testCreatePin() {
    getPinDetail.execute(Params(fakePinId))

    verify(pinRepository).detail(fakePinId)
  }
}
