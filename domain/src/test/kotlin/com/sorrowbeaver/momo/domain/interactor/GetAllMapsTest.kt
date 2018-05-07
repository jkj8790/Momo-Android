package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.GetAllMaps.Params
import com.sorrowbeaver.momo.domain.model.MomoMap.MapSortOption.RECENT
import com.sorrowbeaver.momo.domain.repository.MapRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAllMapsTest {

  @Mock
  private
  lateinit var mapRepository: MapRepository
  private lateinit var getAllMaps: GetAllMaps

  @Before
  fun setUp() {
    `when`(mapRepository.getAllMaps()).thenReturn(Observable.empty())
    getAllMaps = GetAllMaps(mapRepository)
  }

  @Test
  fun testGetMaps() {
    getAllMaps.execute(Params(RECENT))

    verify(mapRepository).getAllMaps(RECENT)
  }
}
