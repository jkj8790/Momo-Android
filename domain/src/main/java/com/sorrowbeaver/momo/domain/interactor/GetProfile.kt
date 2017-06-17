package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.GetProfile.Params
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetProfile (
    val userRepository: UserRepository,
    executorScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<User, Params>(executorScheduler, postExecutionScheduler) {

  data class Params(val userId : Long)

  override fun buildUseCaseObservable(params: Params): Observable<User> {
    return userRepository.detail(params.userId)
  }
}
