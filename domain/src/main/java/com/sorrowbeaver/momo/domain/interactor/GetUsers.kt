package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.GetUsers.Params
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.model.UserSortOption
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetUsers (
    val userRepository: UserRepository,
    executorScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<List<User>, Params>(executorScheduler, postExecutionScheduler) {

  data class Params(val sortOption: UserSortOption)

  override fun buildObservable(params: Params): Observable<List<User>> {
    return userRepository.users(params.sortOption)
  }
}
