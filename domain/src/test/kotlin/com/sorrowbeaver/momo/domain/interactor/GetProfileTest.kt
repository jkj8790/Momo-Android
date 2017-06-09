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

class GetProfileTest {

  val FAKE_USER_ID = 0L

  @Mock lateinit var mockExecutor : ThreadExecutor
  @Mock lateinit var mockPostExecution : PostExecutionThread
  @Mock lateinit var userRepository : UserRepository
  lateinit var getProfile: GetProfile

  @Before fun setUp() {
    MockitoAnnotations.initMocks(this);
    `when`(userRepository.detail(FAKE_USER_ID)).thenReturn(Observable.empty());
    getProfile = GetProfile(userRepository, mockExecutor, mockPostExecution)
  }

  @Test fun testSignUp() {
    getProfile.buildUseCaseObservable(GetProfile.Params(FAKE_USER_ID))

    verify(userRepository).detail(FAKE_USER_ID)
  }

}