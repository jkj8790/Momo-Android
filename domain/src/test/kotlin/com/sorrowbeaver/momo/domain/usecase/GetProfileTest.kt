package com.sorrowbeaver.momo.domain.usecase

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetProfileTest {

  private val fakeUserId = 0L

  @Mock
  private
  lateinit var userRepository: UserRepository
  private lateinit var getProfile: GetProfile

  @Before
  fun setUp() {
    `when`(userRepository.detail(fakeUserId)).thenReturn(Observable.empty())
    getProfile = GetProfile(userRepository)
  }

  @Test
  fun testSignUp() {
    getProfile.execute(GetProfile.Params(fakeUserId))

    verify(userRepository).detail(fakeUserId)
  }
}
