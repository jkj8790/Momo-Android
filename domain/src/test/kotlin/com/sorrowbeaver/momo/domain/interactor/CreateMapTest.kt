package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.CreateMap.Params
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

  @Mock
  private lateinit var mapRepository: MapRepository
  private lateinit var createMap: CreateMap

  private val testName = "name"
  private val testDescription = "description"
  private val testIsPrivate = true
  private val testAuthorId = 1L
  private val testAuthorName = "authorName"

  @Before fun setUp() {
    `when`(mapRepository.createMap(
        testName,
        testDescription,
        testIsPrivate,
        testAuthorId,
        testAuthorName
    )).thenReturn(Observable.empty())
    createMap = CreateMap(mapRepository, TestScheduler(), TestScheduler())
  }

  @Test fun testCreateMap() {
    createMap.buildObservable(Params(
        testName,
        testDescription,
        testIsPrivate,
        testAuthorId,
        testAuthorName
    ))

    verify(mapRepository).createMap(
        testName,
        testDescription,
        testIsPrivate,
        testAuthorId,
        testAuthorName
    )
  }

}
