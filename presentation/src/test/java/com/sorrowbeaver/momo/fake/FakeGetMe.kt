package com.sorrowbeaver.momo.fake

import com.nhaarman.mockito_kotlin.mock
import com.sorrowbeaver.momo.domain.interactor.GetMe
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.model.UserType
import io.reactivex.Observable
import java.util.Date

//TODO It seems better to implement fake user repository
object FakeGetMe : GetMe(mock()) {

  val me = User(
    1L, UserType.Normal, "dog", "dog@email.com",
    "https://i.redd.it/sq3n60qr12bz.jpg", Date(), Date(),
    true, true, listOf(), listOf(), listOf()
  )

  override fun execute(params: Unit): Observable<User> {
    return Observable.just(me)
  }
}