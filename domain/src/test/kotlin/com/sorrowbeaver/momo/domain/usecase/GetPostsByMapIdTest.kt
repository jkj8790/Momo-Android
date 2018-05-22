package com.sorrowbeaver.momo.domain.usecase

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.repository.PostRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPostsByMapIdTest {

  @Mock
  private
  lateinit var postRepository: PostRepository
  private lateinit var getPostsByMapId: GetPostsByMapId
  private val fakeMapId = 0L

  @Before
  fun setUp() {
    `when`(postRepository.getPostsByMapId(fakeMapId)).thenReturn(Observable.empty())
    getPostsByMapId = GetPostsByMapId(postRepository)
  }

  @Test
  fun testCreateMap() {
    getPostsByMapId.execute(GetPostsByMapId.Params(fakeMapId))

    verify(postRepository).getPostsByMapId(fakeMapId)
  }
}
