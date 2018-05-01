package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.Follow.Params
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FollowTest {

  @Mock
  private
  lateinit var userRepository: UserRepository
  private lateinit var follow: Follow

  private val fakeUserId = 0L

  @Before
  fun setUp() {
    `when`(userRepository.follow(fakeUserId)).thenReturn(Observable.empty())
    follow = Follow(userRepository)
  }

  @Test
  fun testSignUp() {
    follow.execute(Params(fakeUserId))

    verify(userRepository).follow(fakeUserId)
  }
}
