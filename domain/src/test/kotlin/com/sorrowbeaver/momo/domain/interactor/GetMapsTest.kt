package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.GetMaps.Params
import com.sorrowbeaver.momo.domain.model.MomoMap.MapSortOption.RECENT
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
class GetMapsTest {

  @Mock
  private
  lateinit var mapRepository: MapRepository
  private lateinit var getMaps: GetMaps

  @Before
  fun setUp() {
    `when`(mapRepository.maps()).thenReturn(Observable.empty())
    getMaps = GetMaps(mapRepository, TestScheduler(), TestScheduler())
  }

  @Test
  fun testGetMaps() {
    getMaps.buildObservable(Params(RECENT))

    verify(mapRepository).maps(RECENT)
  }
}
