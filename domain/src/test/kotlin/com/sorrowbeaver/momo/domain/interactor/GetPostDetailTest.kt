package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.GetPostDetail.Params
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
class GetPostDetailTest {

  @Mock lateinit var postRepository: PostRepository
  lateinit var getPostDetail: GetPostDetail

  val FAKE_POST_ID = 0L

  @Before fun setUp() {
    `when`(postRepository.detail(FAKE_POST_ID)).thenReturn(Observable.empty())
    getPostDetail = GetPostDetail(postRepository, TestScheduler(), TestScheduler())
  }

  @Test fun testCreateMap() {
    getPostDetail.buildObservable(Params(FAKE_POST_ID))

    verify(postRepository).detail(FAKE_POST_ID)
  }

}
