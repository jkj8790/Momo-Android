package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.interactor.GetUsers.Params
import com.sorrowbeaver.momo.domain.model.UserSortOption.FOLLOWER_DESCENDING
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
class GetUsersTest {

  @Mock lateinit var userRepository : UserRepository
  lateinit var getUsers: GetUsers

  val sortOption = FOLLOWER_DESCENDING

  @Before fun setUp() {
    `when`(userRepository.users(sortOption)).thenReturn(Observable.empty());
    getUsers = GetUsers(userRepository, TestScheduler(), TestScheduler())
  }

  @Test fun testSignUp() {
    getUsers.buildUseCaseObservable(Params(sortOption))

    verify(userRepository).users(sortOption)
  }

}
