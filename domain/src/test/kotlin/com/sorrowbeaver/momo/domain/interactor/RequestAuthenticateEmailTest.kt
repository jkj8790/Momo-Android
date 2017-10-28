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
class RequestAuthenticateEmailTest {

  @Mock lateinit var userRepository : UserRepository
  lateinit var requestAuthenticateEmail: RequestAuthenticateEmail

  @Before fun setUp() {
    `when`(userRepository.requestAuthenticateEmail()).thenReturn(Observable.empty());
    requestAuthenticateEmail = RequestAuthenticateEmail(userRepository,
        TestScheduler(), TestScheduler())
  }

  @Test fun testSignUp() {
    requestAuthenticateEmail.buildObservable(Unit)

    verify(userRepository).requestAuthenticateEmail()
  }

}
