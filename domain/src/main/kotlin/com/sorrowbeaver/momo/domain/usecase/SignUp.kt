package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import com.sorrowbeaver.momo.domain.usecase.SignUp.Params
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
import io.reactivex.Observable

class SignUp(
  private val userRepository: UserRepository
) : UseCase<User, Params> {

  data class Params(val email: String, val id: String, val password: String)

  override fun execute(params: Params): Observable<User> {
    return userRepository.signUp(params.email, params.id, params.password)
  }
}
