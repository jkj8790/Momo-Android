package com.sorrowbeaver.momo.domain.repository

import com.sorrowbeaver.momo.domain.model.User
import io.reactivex.Observable
import okhttp3.Response

interface UserRepository {

  fun login(id: String, password: String): Observable<User>

  fun singup(email: String, userName: String, password: String): Observable<User>

  //TODO ordering
  fun users(): Observable<Response>

  fun logout(): Observable<Response>

  fun facebookLogin(): Observable<User>

  fun detail(userId: Long): Observable<User>

  fun follow(userId: Long): Observable<Response>

  fun requestAuthentiacateMail(): Observable<Response>
}

