package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

open class GetMe @Inject constructor(
  val userRepository: UserRepository,
  @Named("executor") executorScheduler: Scheduler,
  @Named("postExecution") postExecutionScheduler: Scheduler
) : UseCase<User, Unit>(executorScheduler, postExecutionScheduler) {

  override fun buildObservable(params: Unit): Observable<User> {
    return userRepository.myDetail()
  }
}
