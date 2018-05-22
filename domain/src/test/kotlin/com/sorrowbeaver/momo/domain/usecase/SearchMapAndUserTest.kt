package com.sorrowbeaver.momo.domain.usecase

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.repository.SearchRepository
import com.sorrowbeaver.momo.domain.usecase.SearchMapAndUser.Params
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchMapAndUserTest {

  @Mock
  private
  lateinit var searchRepository: SearchRepository
  private lateinit var searchMapAndUser: SearchMapAndUser

  private val fakeKeyword = "keyword"

  @Before
  fun setUp() {
    `when`(searchRepository.searchMapAndUser(fakeKeyword)).thenReturn(Observable.empty())
    searchMapAndUser = SearchMapAndUser(searchRepository)
  }

  @Test
  fun testCreateMap() {
    searchMapAndUser.execute(Params(fakeKeyword))

    verify(searchRepository).searchMapAndUser(fakeKeyword)
  }
}
