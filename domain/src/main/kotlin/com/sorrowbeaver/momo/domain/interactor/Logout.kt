package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import okhttp3.Response

class Logout(
  val userRepository: UserRepository
) : UseCase<Response, Unit> {

  override fun execute(params: Unit): Observable<Response> {
    return userRepository.logout()
  }
}
