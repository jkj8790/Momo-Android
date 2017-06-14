package com.sorrowbeaver.momo.data.repository.datasource

import com.sorrowbeaver.momo.data.entity.UserEntity
import io.reactivex.Observable
import java.util.Date

class FakeUserDataStore : UserDataStore {
  override fun login(id: String, password: String): Observable<UserEntity> {
    return Observable.just(UserEntity(
        0L, "username", "password", "email@email.com",
        "http://cs301113.userapi.com/v301113396/30c7/qDFlRAPUNdw.jpg",
        listOf(), Date(), Date(),
        true, true, true, true, listOf()
    ))
  }

}
