package com.sorrowbeaver.momo.dummy

import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.model.UserSortOption
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import okhttp3.Response

object DummyUserRepository : UserRepository {
  override fun login(id: String, password: String): Observable<User> {
    throw NotImplementedError()
  }

  override fun signUp(email: String, userName: String, password: String): Observable<User> {
    throw NotImplementedError()
  }

  override fun facebookLogin(): Observable<User> {
    throw NotImplementedError()
  }

  override fun requestAuthenticateEmail(): Observable<Response> {
    throw NotImplementedError()
  }

  override fun logout(): Observable<Response> {
    throw NotImplementedError()
  }

  override fun detail(userId: Long): Observable<User> {
    throw NotImplementedError()
  }

  override fun myDetail(): Observable<User> {
    throw NotImplementedError()
  }

  override fun users(sortOption: UserSortOption): Observable<List<User>> {
    throw NotImplementedError()
  }

  override fun follow(userId: Long): Observable<Response> {
    throw NotImplementedError()
  }
}