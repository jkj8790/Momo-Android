package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
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
class GetProfileTest {

  private val FAKE_USER_ID = 0L

  @Mock
  private
  lateinit var userRepository: UserRepository
  private lateinit var getProfile: GetProfile

  @Before
  fun setUp() {
    `when`(userRepository.detail(FAKE_USER_ID)).thenReturn(Observable.empty())
    getProfile = GetProfile(userRepository, TestScheduler(), TestScheduler())
  }

  @Test
  fun testSignUp() {
    getProfile.buildObservable(GetProfile.Params(FAKE_USER_ID))

    verify(userRepository).detail(FAKE_USER_ID)
  }
}
