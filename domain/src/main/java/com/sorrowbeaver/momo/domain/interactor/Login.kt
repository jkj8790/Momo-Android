package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.Login.Params
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

open class Login (
    private val userRepository: UserRepository,
    executorScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<User, Params>(executorScheduler, postExecutionScheduler) {

  data class Params(val id: String, val password: String)

  override fun buildObservable(params: Params): Observable<User> {
    return userRepository.login(params.id, params.password)
  }
}
