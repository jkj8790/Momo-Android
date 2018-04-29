package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.Follow.Params
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FollowTest {

  @Mock
  lateinit var userRepository: UserRepository
  lateinit var follow: Follow

  val FAKE_USER_ID = 0L

  @Before
  fun setUp() {
    `when`(userRepository.follow(FAKE_USER_ID)).thenReturn(Observable.empty())
    follow = Follow(userRepository, TestScheduler(), TestScheduler())
  }

  @Test
  fun testSignUp() {
    follow.buildObservable(Params(FAKE_USER_ID))

    verify(userRepository).follow(FAKE_USER_ID)
  }
}
