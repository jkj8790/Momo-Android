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
class CreateMapTest {

  @Mock lateinit var mapRepository: MapRepository
  lateinit var createMap: CreateMap

  @Before fun setUp() {
    `when`(mapRepository.createMap()).thenReturn(Observable.empty())
    createMap = CreateMap(mapRepository, TestScheduler(), TestScheduler())
  }

  @Test fun testCreateMap() {
    createMap.buildUseCaseObservable(Unit)

    verify(mapRepository).createMap()
  }

}
