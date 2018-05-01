package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.Follow.Params
import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import okhttp3.Response

class Follow(
  val userRepository: UserRepository
) : UseCase<Response, Params> {

  data class Params(val userId: Long)

  override fun execute(params: Params): Observable<Response> {
    return userRepository.follow(params.userId)
  }
}
