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
class GetAllPostsTest {

  @Mock
  private
  lateinit var postRepository: PostRepository
  private lateinit var getAllPosts: GetAllPosts

  @Before
  fun setUp() {
    `when`(postRepository.getAllPosts()).thenReturn(Observable.empty())
    getAllPosts = GetAllPosts(postRepository)
  }

  @Test
  fun testCreateMap() {
    getAllPosts.execute(Unit)

    verify(postRepository).getAllPosts()
  }
}
