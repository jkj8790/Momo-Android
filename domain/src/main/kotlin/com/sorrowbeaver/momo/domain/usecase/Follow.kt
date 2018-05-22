package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.repository.UserRepository
import com.sorrowbeaver.momo.domain.usecase.Follow.Params
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
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
