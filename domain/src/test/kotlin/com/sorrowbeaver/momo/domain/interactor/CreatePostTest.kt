package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.CreatePost.Params
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
class CreatePostTest {

  @Mock
  private
  lateinit var postRepository: PostRepository
  private lateinit var createPost: CreatePost

  private val fakePinId = 0L
  private val fakePhotoUrl = "photo"
  private val fakeDescription = "description"

  @Before
  fun setUp() {
    `when`(
      postRepository.createPost(
        fakePinId,
        fakePhotoUrl,
        fakeDescription
      )
    ).thenReturn(Observable.empty())
    createPost = CreatePost(postRepository, TestScheduler(), TestScheduler())
  }

  @Test
  fun testCreateMap() {
    createPost.buildObservable(
      Params(
        fakePinId, fakePhotoUrl, fakeDescription
      )
    )

    verify(postRepository).createPost(
      fakePinId,
      fakePhotoUrl, fakeDescription
    )
  }
}
