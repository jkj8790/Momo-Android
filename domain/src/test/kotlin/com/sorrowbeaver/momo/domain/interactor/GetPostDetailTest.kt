package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.GetPostDetail.Params
import com.sorrowbeaver.momo.domain.repository.PostRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPostDetailTest {

  @Mock
  private
  lateinit var postRepository: PostRepository
  private lateinit var getPostDetail: GetPostDetail

  private val fakePostId = 0L

  @Before
  fun setUp() {
    `when`(postRepository.detail(fakePostId)).thenReturn(Observable.empty())
    getPostDetail = GetPostDetail(postRepository)
  }

  @Test
  fun testCreateMap() {
    getPostDetail.execute(Params(fakePostId))

    verify(postRepository).detail(fakePostId)
  }
}
