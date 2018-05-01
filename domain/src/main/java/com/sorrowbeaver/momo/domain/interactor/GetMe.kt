package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetMe @Inject constructor(
  private val userRepository: UserRepository
) : UseCase<User, Unit> {

  override fun execute(params: Unit): Observable<User> {
    return userRepository.myDetail()
  }
}
