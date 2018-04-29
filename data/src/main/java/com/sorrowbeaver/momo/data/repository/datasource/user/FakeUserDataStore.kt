package com.sorrowbeaver.momo.data.repository.datasource.user

import com.sorrowbeaver.momo.data.entity.UserEntity
import io.reactivex.Observable
import okhttp3.Response
import java.util.Date
import javax.inject.Inject

class FakeUserDataStore @Inject constructor() : UserDataStore {

  override fun login(id: String, password: String): Observable<UserEntity> {
    return Observable.just(me)
  }

  override fun signUp(email: String, userName: String, password: String): Observable<UserEntity> {
    return Observable.just(me)
  }

  override fun facebookLogin(): Observable<UserEntity> {
    return Observable.just(me)
  }

  override fun detail(userId: Long): Observable<UserEntity> {
    return Observable.just(fakeUsers[userId.toInt()])
  }

  override fun myDetail(): Observable<UserEntity> {
    // TODO Throw exception if not logged in
    return Observable.just(me)
  }

  override fun follow(userId: Long): Observable<Response> {
    TODO("not implemented")
  }

  override fun requestAuthenticateEmail(): Observable<Response> {
    TODO("not implemented")
  }

  private fun Response.Builder.success(): Response = code(200).build()

  override fun logout(): Observable<Response> {
    return Observable.just(Response.Builder().success())
  }

  override fun users(): Observable<List<UserEntity>> {
    return Observable.just(fakeUsers.subList(1, fakeUsers.size - 1))
  }

  //TODO check image license..
  private val me = UserEntity(
    0L, "me", "password", "me@email.com",
    "http://cs301113.userapi.com/v301113396/30c7/qDFlRAPUNdw.jpg",
    listOf(), Date(), Date(),
    true, true, true, true, listOf()
  )

  private val fakeUsers = listOf(
    me,
    UserEntity(
      1L, "dog", "password", "odg@email.com",
      "https://i.redd.it/sq3n60qr12bz.jpg",
      listOf(), Date(), Date(),
      true, true, true, true, listOf()
    ),
    UserEntity(
      2L, "cat", "password", "cat@email.com",
      "http://i.imgur.com/ji3jJMu.jpg",
      listOf(), Date(), Date(),
      true, true, true, true, listOf()
    )
  )
}
