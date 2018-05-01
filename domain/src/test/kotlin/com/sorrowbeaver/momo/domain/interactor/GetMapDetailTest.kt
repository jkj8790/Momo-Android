package com.sorrowbeaver.momo.domain.interactor

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
class GetMapDetailTest {

  private val fakeMapId = 0L

  @Mock
  private
  lateinit var mapRepository: MapRepository
  private lateinit var getMapDetail: GetMapDetail

  @Before
  fun setUp() {
    `when`(mapRepository.detail(fakeMapId)).thenReturn(Observable.empty())
    getMapDetail = GetMapDetail(mapRepository)
  }

  @Test
  fun testMapDetail() {
    getMapDetail.execute(GetMapDetail.Params(fakeMapId))

    verify(mapRepository).detail(fakeMapId)
  }
}
