package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.sorrowbeaver.momo.domain.interactor.CreateMap.Params
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.repository.MapRepository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
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

  @Before
  fun setUp() {
    `when`(
      mapRepository.createMap(
        testName,
        testDescription,
        testIsPrivate,
        testAuthorId
      )
    ).thenReturn(Observable.empty())
    createMap = CreateMap(mapRepository)
  }

  @Test
  fun testCreateMapSuccess() {
    val validParams = Params(
      testName,
      testDescription,
      testIsPrivate,
      testAuthorId
    )

    createMap.execute(
      validParams
    )

    verify(mapRepository).createMap(
      testName,
      testDescription,
      testIsPrivate,
      testAuthorId
    )
  }

  @Test
  fun testCreateMapFailWhenNameIsEmpty() {
    val emptyNameParams = Params("", "", false, 0L)
    val testObserver = TestObserver<MomoMap>()

    createMap.execute(emptyNameParams)
      .subscribe(testObserver)
    verifyZeroInteractions(mapRepository)
    testObserver.assertError(IllegalArgumentException::class.java)
  }

  @Test
  fun testCreateMapFailWhenNameIsShort() {
    val oneCharNameParams = Params("K", "", false, 0L)
    val testObserver = TestObserver<MomoMap>()

    createMap.execute(oneCharNameParams)
      .subscribe(testObserver)
    verifyZeroInteractions(mapRepository)
    testObserver.assertError(IllegalArgumentException::class.java)
  }
}
