package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.repository.PostRepository
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPostsTest {

  @Mock lateinit var postRepository: PostRepository
  lateinit var getPosts: GetPosts

  @Before fun setUp() {
    `when`(postRepository.posts()).thenReturn(Observable.empty())
    getPosts = GetPosts(postRepository, TestScheduler(), TestScheduler())
  }

  @Test fun testCreateMap() {
    getPosts.buildUseCaseObservable(Unit)

    verify(postRepository).posts()
  }

}
