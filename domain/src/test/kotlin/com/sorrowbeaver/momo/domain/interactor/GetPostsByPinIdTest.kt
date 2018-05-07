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
class GetPostsByPinIdTest {

  @Mock
  private
  lateinit var postRepository: PostRepository
  private lateinit var getPostsByPinId: GetPostsByPinId
  private val fakePinId = 0L

  @Before
  fun setUp() {
    `when`(postRepository.getPostsByPinId(fakePinId)).thenReturn(Observable.empty())
    getPostsByPinId = GetPostsByPinId(postRepository)
  }

  @Test
  fun testCreatePin() {
    getPostsByPinId.execute(GetPostsByPinId.Params(fakePinId))

    verify(postRepository).getPostsByPinId(fakePinId)
  }
}
