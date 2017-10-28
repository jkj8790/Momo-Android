package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import okhttp3.Response

class RequestAuthenticateEmail (
    val userRepository: UserRepository,
    executorScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<Response, Unit>(executorScheduler, postExecutionScheduler) {

  override fun buildObservable(params: Unit): Observable<Response> {
    return userRepository.requestAuthenticateEmail()
  }
}
