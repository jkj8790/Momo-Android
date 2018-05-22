package com.sorrowbeaver.momo.domain.usecase

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.repository.MapRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMapsByUserIdTest {

  @Mock
  private
  lateinit var mapRepository: MapRepository
  private lateinit var getAllMaps: GetMapsByUserId
  private val fakeUserId = 0L

  @Before
  fun setUp() {
    `when`(mapRepository.getMapsByUserId(fakeUserId)).thenReturn(Observable.empty())
    getAllMaps = GetMapsByUserId(mapRepository)
  }

  @Test
  fun testGetMaps() {
    getAllMaps.execute(GetMapsByUserId.Params(fakeUserId))

    verify(mapRepository).getMapsByUserId(fakeUserId)
  }
}
