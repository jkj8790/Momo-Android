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
class LoginTest {

  val FAKE_ID = "id"
  val FAKE_PWD = "pwd"

  @Mock lateinit var userRepository : UserRepository
  lateinit var login : Login

  @Before fun setUp() {
    `when`(userRepository.login(FAKE_ID, FAKE_PWD)).thenReturn(Observable.empty())
    login = Login(userRepository, TestScheduler(), TestScheduler())
  }

  @Test fun testLogin() {
    login.buildObservable(Login.Params(FAKE_ID, FAKE_PWD))

    verify(userRepository).login(FAKE_ID, FAKE_PWD);
  }

}
