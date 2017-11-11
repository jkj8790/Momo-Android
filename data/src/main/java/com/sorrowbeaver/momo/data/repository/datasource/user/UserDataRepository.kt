package com.sorrowbeaver.momo.data.repository.datasource.user

import com.sorrowbeaver.momo.data.entity.mapper.UserEntityDataMapper
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.model.UserSortOption
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import okhttp3.Response

class UserDataRepository : UserRepository {

  val userEntityDataMapper = UserEntityDataMapper()
  val userDataStoreFactory = UserDataStoreFactory()

  override fun login(id: String, password: String): Observable<User> {
    val userDataStore = userDataStoreFactory.create()
    return userDataStore.login(id, password)
        .map {userEntityDataMapper.transform(it) }
  }

  override fun signUp(email: String, userName: String, password: String): Observable<User> {
    val userDataStore = userDataStoreFactory.create()
    return userDataStore.signUp(email, userName, password)
        .map {userEntityDataMapper.transform(it) }

  }

  override fun facebookLogin(): Observable<User> {
    val userDataStore = userDataStoreFactory.create()
    return userDataStore.facebookLogin()
        .map {userEntityDataMapper.transform(it) }
  }

  override fun requestAuthenticateEmail(): Observable<Response> {
    val userDataStore = userDataStoreFactory.create()
    return userDataStore.requestAuthenticateEmail()
  }

  override fun logout(): Observable<Response> {
    val userDataStore = userDataStoreFactory.create()
    return userDataStore.logout()
  }

  override fun detail(userId: Long): Observable<User> {
    val userDataStore = userDataStoreFactory.create()
    return userDataStore.detail(userId)
        .map { userEntityDataMapper.transform(it) }
  }

  override fun myDetail(): Observable<User> {
    val userDataStore = userDataStoreFactory.create()
    return userDataStore.myDetail()
        .map { userEntityDataMapper.transform(it) }
  }

  override fun users(sortOption: UserSortOption): Observable<List<User>> {
    val userDataStore = userDataStoreFactory.create()
    return userDataStore.users()
        .flatMap { Observable.fromIterable(it) }
        .map { userEntityDataMapper.transform(it) }
        .toList()
        .toObservable()
  }

  override fun follow(userId: Long): Observable<Response> {
    val userDataStore = userDataStoreFactory.create()
    return userDataStore.follow(userId)
  }

}
