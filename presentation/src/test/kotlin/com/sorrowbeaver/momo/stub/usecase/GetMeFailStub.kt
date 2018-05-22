package com.sorrowbeaver.momo.stub.usecase

import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.usecase.GetMe
import com.sorrowbeaver.momo.dummy.DummyUserRepository
import io.reactivex.Observable

object GetMeFailStub : GetMe(DummyUserRepository) {

  override fun execute(params: Unit): Observable<User> {
    return Observable.error(RuntimeException())
  }
}