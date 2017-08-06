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

  @Mock lateinit var postRepository: PostRepository
  lateinit var createPost: CreatePost

  val FAKE_PIN_ID = 0L
  val FAKE_PHOTO_URL = "photo"
  val FAKE_DESCRIPTION = "description"

  @Before fun setUp() {
    `when`(postRepository.createPost(FAKE_PIN_ID,
         FAKE_PHOTO_URL, FAKE_DESCRIPTION)).thenReturn(Observable.empty())
    createPost = CreatePost(postRepository, TestScheduler(), TestScheduler())
  }

  @Test fun testCreateMap() {
    createPost.buildUseCaseObservable(Params(
        FAKE_PIN_ID, FAKE_PHOTO_URL, FAKE_DESCRIPTION
    ))

    verify(postRepository).createPost(FAKE_PIN_ID,
         FAKE_PHOTO_URL, FAKE_DESCRIPTION)
  }

}
