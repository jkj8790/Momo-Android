package com.sorrowbeaver.momo.domain.repository

import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.model.UserSortOption
import io.reactivex.Observable
import okhttp3.Response

interface UserRepository {
  //TODO Response depends on HTTP specification. Better if implement own response object
  //TODO And consider own exception object too.

  fun login(id: String, password: String): Observable<User>

  fun signUp(email: String, userName: String, password: String): Observable<User>

  fun facebookLogin(): Observable<User>

  fun requestAuthenticateEmail(): Observable<Response>

  fun logout(): Observable<Response>

  fun detail(userId: Long): Observable<User>

  fun users(sortOption: UserSortOption): Observable<List<User>>

  fun follow(userId: Long): Observable<Response>
}

