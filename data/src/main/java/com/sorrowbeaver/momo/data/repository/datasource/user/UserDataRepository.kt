package com.sorrowbeaver.momo.data.repository.datasource.user

import com.sorrowbeaver.momo.data.entity.mapper.UserEntityDataMapper
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.model.UserSortOption
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import okhttp3.Response
import javax.inject.Inject

class UserDataRepository @Inject constructor(
  private val userDataStore: UserDataStore,
  private val userEntityDataMapper: UserEntityDataMapper
) : UserRepository {

  override fun login(id: String, password: String): Observable<User> {
    return userDataStore.login(id, password)
      .map { userEntityDataMapper.transform(it) }
  }

  override fun signUp(email: String, userName: String, password: String): Observable<User> {
    return userDataStore.signUp(email, userName, password)
      .map { userEntityDataMapper.transform(it) }
  }

  override fun facebookLogin(): Observable<User> {
    return userDataStore.facebookLogin()
      .map { userEntityDataMapper.transform(it) }
  }

  override fun requestAuthenticateEmail(): Observable<Response> {
    return userDataStore.requestAuthenticateEmail()
  }

  override fun logout(): Observable<Response> {
    return userDataStore.logout()
  }

  override fun detail(userId: Long): Observable<User> {
    return userDataStore.detail(userId)
      .map { userEntityDataMapper.transform(it) }
  }

  override fun myDetail(): Observable<User> {
    return userDataStore.myDetail()
      .map { userEntityDataMapper.transform(it) }
  }

  override fun users(sortOption: UserSortOption): Observable<List<User>> {
    return userDataStore.users()
      .flatMap { Observable.fromIterable(it) }
      .map { userEntityDataMapper.transform(it) }
      .toList()
      .toObservable()
  }

  override fun follow(userId: Long): Observable<Response> {
    return userDataStore.follow(userId)
  }
}
