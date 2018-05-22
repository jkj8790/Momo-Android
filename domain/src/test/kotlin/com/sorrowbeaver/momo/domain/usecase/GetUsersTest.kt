package com.sorrowbeaver.momo.domain.usecase

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.model.UserSortOption.FOLLOWER_DESCENDING
import com.sorrowbeaver.momo.domain.repository.UserRepository
import com.sorrowbeaver.momo.domain.usecase.GetUsers.Params
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetUsersTest {

  @Mock
  private
  lateinit var userRepository: UserRepository
  private lateinit var getUsers: GetUsers

  private val sortOption = FOLLOWER_DESCENDING

  @Before
  fun setUp() {
    `when`(userRepository.users(sortOption)).thenReturn(Observable.empty())
    getUsers = GetUsers(userRepository)
  }

  @Test
  fun testSignUp() {
    getUsers.execute(Params(sortOption))

    verify(userRepository).users(sortOption)
  }
}
