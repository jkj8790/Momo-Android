package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.GetProfile.Params
import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable

class GetProfile(
  private val userRepository: UserRepository
) : UseCase<User, Params> {

  data class Params(val userId: Long)

  override fun execute(params: Params): Observable<User> {
    return userRepository.detail(params.userId)
  }
}
