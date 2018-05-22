package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import com.sorrowbeaver.momo.domain.usecase.Login.Params
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
import io.reactivex.Observable
import javax.inject.Inject

open class Login @Inject constructor(
  private val userRepository: UserRepository
) : UseCase<User, Params> {

  data class Params(val id: String, val password: String)

  override fun execute(params: Params): Observable<User> {
    return userRepository.login(params.id, params.password)
  }
}
