package com.sorrowbeaver.momo.stub

import com.sorrowbeaver.momo.domain.interactor.GetMe
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.dummy.DummyUserRepository
import io.reactivex.Observable

class FailGetMe : GetMe(DummyUserRepository) {

  override fun execute(params: Unit): Observable<User> {
    return Observable.error(RuntimeException())
  }
}