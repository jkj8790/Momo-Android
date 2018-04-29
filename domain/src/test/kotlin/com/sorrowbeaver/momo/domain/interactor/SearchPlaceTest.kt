package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.repository.SearchRepository
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchPlaceTest {

  @Mock
  private
  lateinit var searchRepository: SearchRepository
  private lateinit var searchPlace: SearchPlace

  val FAKE_KEYWORD = "keyword"

  @Before
  fun setUp() {
    `when`(searchRepository.searchPlace()).thenReturn(Observable.empty())
    searchPlace = SearchPlace(searchRepository, TestScheduler(), TestScheduler())
  }

  @Test
  fun testCreateMap() {
    searchPlace.buildObservable(Unit)

    verify(searchRepository).searchPlace()
  }
}
