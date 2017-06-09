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

class SignUpTest {

  val FAKE_EMAIL = "email";
  val FAKE_ID = "id"
  val FAKE_PWD = "pwd"

  @Mock lateinit var mockExecutor : ThreadExecutor
  @Mock lateinit var mockPostExecution : PostExecutionThread
  @Mock lateinit var userRepository : UserRepository
  lateinit var signUp: SignUp

  @Before fun setUp() {
    MockitoAnnotations.initMocks(this);
    `when`(userRepository.signUp(FAKE_EMAIL, FAKE_ID, FAKE_PWD)).thenReturn(Observable.empty());
    signUp = SignUp(userRepository, mockExecutor, mockPostExecution)
  }

  @Test fun testSignUp() {
    signUp.buildUseCaseObservable(SignUp.Params(FAKE_EMAIL, FAKE_ID, FAKE_PWD))

    verify(userRepository).signUp(FAKE_EMAIL, FAKE_ID, FAKE_PWD)
  }

}