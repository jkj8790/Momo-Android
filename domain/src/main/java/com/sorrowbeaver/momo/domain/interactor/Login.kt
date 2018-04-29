package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.Login.Params
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

open class Login @Inject constructor(
  private val userRepository: UserRepository,
  @Named("executor") executorScheduler: Scheduler,
  @Named("postExecution") postExecutionScheduler: Scheduler
) : UseCase<User, Params>(executorScheduler, postExecutionScheduler) {

  data class Params(val id: String, val password: String)

  override fun buildObservable(params: Params): Observable<User> {
    return userRepository.login(params.id, params.password)
  }
}
