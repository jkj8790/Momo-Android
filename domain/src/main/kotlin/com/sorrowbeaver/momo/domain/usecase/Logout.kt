package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.repository.UserRepository
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
import io.reactivex.Observable
import okhttp3.Response

class Logout(
  val userRepository: UserRepository
) : UseCase<Response, Unit> {

  override fun execute(params: Unit): Observable<Response> {
    return userRepository.logout()
  }
}
