package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import com.sorrowbeaver.momo.domain.usecase.GetProfile.Params
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
import io.reactivex.Observable

class GetProfile(
  private val userRepository: UserRepository
) : UseCase<User, Params> {

  data class Params(val userId: Long)

  override fun execute(params: Params): Observable<User> {
    return userRepository.detail(params.userId)
  }
}
