package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.executor.PostExecutionThread
import com.sorrowbeaver.momo.domain.executor.ThreadExecutor
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LoginTest {

  val FAKE_ID = "id"
  val FAKE_PWD = "pwd"

  @Mock lateinit var mockExecutor : ThreadExecutor
  @Mock lateinit var mockPostExecution : PostExecutionThread
  @Mock lateinit var userRepository : UserRepository
  lateinit var login : Login

  @Before fun setUp() {
    MockitoAnnotations.initMocks(this);
    `when`(userRepository.login(FAKE_ID, FAKE_PWD)).thenReturn(Observable.empty());
    login = Login(userRepository, mockExecutor, mockPostExecution)
  }

  @Test fun testLogin() {
    login.buildUseCaseObservable(Login.Params(FAKE_ID, FAKE_PWD))

    verify(userRepository).login(FAKE_ID, FAKE_PWD);
  }

}