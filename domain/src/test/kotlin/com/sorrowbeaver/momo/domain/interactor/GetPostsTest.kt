package com.sorrowbeaver.momo.domain.interactor

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
class GetPostsTest {

  @Mock
  private
  lateinit var postRepository: PostRepository
  private lateinit var getPosts: GetPosts

  @Before
  fun setUp() {
    `when`(postRepository.posts()).thenReturn(Observable.empty())
    getPosts = GetPosts(postRepository)
  }

  @Test
  fun testCreateMap() {
    getPosts.execute(Unit)

    verify(postRepository).posts()
  }
}
