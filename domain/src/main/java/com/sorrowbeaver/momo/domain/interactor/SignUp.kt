package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.SignUp.Params
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class SignUp (
    val userRepository: UserRepository,
    executorScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<User, Params>(executorScheduler, postExecutionScheduler) {

  data class Params(val email: String, val id: String, val password: String)

  override fun buildObservable(params: Params): Observable<User> {
    return userRepository.signUp(params.email, params.id, params.password)
  }
}
