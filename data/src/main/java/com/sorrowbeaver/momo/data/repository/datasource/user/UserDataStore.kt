package com.sorrowbeaver.momo.data.repository.datasource.user

import com.sorrowbeaver.momo.data.entity.UserEntity
import io.reactivex.Observable
import okhttp3.Response

interface UserDataStore {

  fun login(id: String, password: String) : Observable<UserEntity>

  fun signUp(email: String, userName: String, password: String): Observable<UserEntity>

  fun facebookLogin(): Observable<UserEntity>

  fun detail(userId: Long): Observable<UserEntity>

  fun follow(userId: Long): Observable<Response>

  fun requestAuthenticateMail(): Observable<Response>

  fun logout(): Observable<Response>

  fun users(): Observable<List<UserEntity>>
}