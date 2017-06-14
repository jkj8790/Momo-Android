package com.sorrowbeaver.momo.data.repository.datasource

import com.sorrowbeaver.momo.data.entity.UserEntity
import io.reactivex.Observable

interface UserDataStore {

  fun login(id: String, password: String) : Observable<UserEntity>

}
