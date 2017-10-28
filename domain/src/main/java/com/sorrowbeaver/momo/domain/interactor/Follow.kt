package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.Follow.Params
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import okhttp3.Response

class Follow (
    val userRepository: UserRepository,
    executorScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<Response, Params>(executorScheduler, postExecutionScheduler) {

  data class Params(val userId: Long)

  override fun buildObservable(params: Params): Observable<Response> {
    return userRepository.follow(params.userId)
  }
}
