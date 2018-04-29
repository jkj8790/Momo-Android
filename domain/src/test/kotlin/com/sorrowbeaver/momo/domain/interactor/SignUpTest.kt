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

  private val fakeEmail = "email"
  private val fakeId = "id"
  private val fakePwd = "pwd"

  @Mock
  lateinit var mockExecutor: ThreadExecutor
  @Mock
  lateinit var mockPostExecution: PostExecutionThread
  @Mock
  private
  lateinit var userRepository: UserRepository
  private lateinit var signUp: SignUp

  @Before
  fun setUp() {
    `when`(userRepository.signUp(fakeEmail, fakeId, fakePwd)).thenReturn(Observable.empty())
    signUp = SignUp(userRepository, TestScheduler(), TestScheduler())
  }

  @Test
  fun testSignUp() {
    signUp.buildObservable(SignUp.Params(fakeEmail, fakeId, fakePwd))

    verify(userRepository).signUp(fakeEmail, fakeId, fakePwd)
  }
}
