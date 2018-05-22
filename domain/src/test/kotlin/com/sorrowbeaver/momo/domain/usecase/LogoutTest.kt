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
class LogoutTest {

  @Mock
  private
  lateinit var userRepository: UserRepository
  private lateinit var logout: Logout

  @Before
  fun setUp() {
    `when`(userRepository.logout()).thenReturn(Observable.empty())
    logout = Logout(userRepository)
  }

  @Test
  fun testSignUp() {
    logout.execute(Unit)

    verify(userRepository).logout()
  }
}
