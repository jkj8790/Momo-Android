package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.repository.MapRepository
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMapDetailTest {

  val FAKE_MAP_ID = 0L

  @Mock lateinit var mapRepository: MapRepository
  lateinit var getMapDetail: GetMapDetail

  @Before fun setUp() {
    `when`(mapRepository.detail(FAKE_MAP_ID)).thenReturn(Observable.empty())
    getMapDetail = GetMapDetail(mapRepository, TestScheduler(), TestScheduler())
  }

  @Test fun testMapDetail() {
    getMapDetail.buildUseCaseObservable(GetMapDetail.Params(FAKE_MAP_ID))

    verify(mapRepository).detail(FAKE_MAP_ID)
  }

}