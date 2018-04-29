package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.executor.PostExecutionThread
import com.sorrowbeaver.momo.domain.executor.ThreadExecutor
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
class SignUpTest {

  val FAKE_EMAIL = "email"
  val FAKE_ID = "id"
  val FAKE_PWD = "pwd"

  @Mock
  lateinit var mockExecutor: ThreadExecutor
  @Mock
  lateinit var mockPostExecution: PostExecutionThread
  @Mock
  lateinit var userRepository: UserRepository
  lateinit var signUp: SignUp

  @Before
  fun setUp() {
    `when`(userRepository.signUp(FAKE_EMAIL, FAKE_ID, FAKE_PWD)).thenReturn(Observable.empty())
    signUp = SignUp(userRepository, TestScheduler(), TestScheduler())
  }

  @Test
  fun testSignUp() {
    signUp.buildObservable(SignUp.Params(FAKE_EMAIL, FAKE_ID, FAKE_PWD))

    verify(userRepository).signUp(FAKE_EMAIL, FAKE_ID, FAKE_PWD)
  }
}
