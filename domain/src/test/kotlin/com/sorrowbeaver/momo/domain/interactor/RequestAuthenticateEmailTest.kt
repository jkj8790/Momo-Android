package com.sorrowbeaver.momo.domain.interactor

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
class RequestAuthenticateEmailTest {

  @Mock
  private
  lateinit var userRepository: UserRepository
  private lateinit var requestAuthenticateEmail: RequestAuthenticateEmail

  @Before
  fun setUp() {
    `when`(userRepository.requestAuthenticateEmail()).thenReturn(Observable.empty())
    requestAuthenticateEmail = RequestAuthenticateEmail(userRepository)
  }

  @Test
  fun testSignUp() {
    requestAuthenticateEmail.execute(Unit)

    verify(userRepository).requestAuthenticateEmail()
  }
}
