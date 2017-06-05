package com.sorrowbeaver.momo.domain.repository

import com.sorrowbeaver.momo.domain.model.User
import io.reactivex.Observable

interface UserRepository {

  fun login() : Observable<User>

}

