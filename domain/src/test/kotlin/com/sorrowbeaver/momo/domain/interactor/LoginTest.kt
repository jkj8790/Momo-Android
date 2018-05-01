package com.sorrowbeaver.momo.domain.interactor

import com.nhaarman.mockito_kotlin.verify
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginTest {

  private val fakeId = "id"
  private val fakePwd = "pwd"

  @Mock
  private
  lateinit var userRepository: UserRepository
  private lateinit var login: Login

  @Before
  fun setUp() {
    `when`(userRepository.login(fakeId, fakePwd)).thenReturn(Observable.empty())
    login = Login(userRepository)
  }

  @Test
  fun testLogin() {
    login.execute(Login.Params(fakeId, fakePwd))

    verify(userRepository).login(fakeId, fakePwd)
  }
}
