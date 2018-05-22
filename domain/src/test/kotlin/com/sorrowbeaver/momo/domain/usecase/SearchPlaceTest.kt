package com.sorrowbeaver.momo.domain.usecase

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.repository.SearchRepository
import io.reactivex.Observable
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

  val fakeKeyword = "keyword"

  @Before
  fun setUp() {
    `when`(searchRepository.searchPlace()).thenReturn(Observable.empty())
    searchPlace = SearchPlace(searchRepository)
  }

  @Test
  fun testCreateMap() {
    searchPlace.execute(Unit)

    verify(searchRepository).searchPlace()
  }
}
