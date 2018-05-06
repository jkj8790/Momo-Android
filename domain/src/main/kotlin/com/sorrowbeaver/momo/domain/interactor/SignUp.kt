package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.SignUp.Params
import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable

class SignUp(
  private val userRepository: UserRepository
) : UseCase<User, Params> {

  data class Params(val email: String, val id: String, val password: String)

  override fun execute(params: Params): Observable<User> {
    return userRepository.signUp(params.email, params.id, params.password)
  }
}
