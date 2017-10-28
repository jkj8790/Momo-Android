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
class LogoutTest {

  @Mock lateinit var userRepository : UserRepository
  lateinit var logout: Logout

  @Before fun setUp() {
    `when`(userRepository.logout()).thenReturn(Observable.empty());
    logout = Logout(userRepository, TestScheduler(), TestScheduler())
  }

  @Test fun testSignUp() {
    logout.buildObservable(Unit)

    verify(userRepository).logout()
  }

}
