package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.SearchMapAndUser.Params
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
class SearchMapAndUserTest {

  @Mock lateinit var searchRepository: SearchRepository
  lateinit var searchMapAndUser: SearchMapAndUser

  val FAKE_KEYWORD = "keyword"

  @Before fun setUp() {
    `when`(searchRepository.searchMapAndUser(FAKE_KEYWORD)).thenReturn(Observable.empty())
    searchMapAndUser = SearchMapAndUser(searchRepository, TestScheduler(), TestScheduler())
  }

  @Test fun testCreateMap() {
    searchMapAndUser.buildObservable(Params(FAKE_KEYWORD))

    verify(searchRepository).searchMapAndUser(FAKE_KEYWORD)
  }

}
