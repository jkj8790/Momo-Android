package com.sorrowbeaver.momo.data.repository

import com.sorrowbeaver.momo.data.entity.mapper.UserEntityDataMapper
import com.sorrowbeaver.momo.data.repository.datasource.UserDataStoreFactory
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import okhttp3.Response

class UserDataRepository : UserRepository {

  val userEntityDataMapper = UserEntityDataMapper()
  val userDataStoreFactory = UserDataStoreFactory()

  override fun login(id: String, password: String): Observable<User> {
    val userDataStore = userDataStoreFactory.create()
    return userDataStore.login(id, password).map {userEntityDataMapper.transform(it) }
  }

  override fun signUp(email: String, userName: String, password: String): Observable<User> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun users(): Observable<Response> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun logout(): Observable<Response> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun facebookLogin(): Observable<User> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun detail(userId: Long): Observable<User> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun follow(userId: Long): Observable<Response> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun requestAuthentiacateMail(): Observable<Response> {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }



}
