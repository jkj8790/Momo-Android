package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.executor.PostExecutionThread
import com.sorrowbeaver.momo.domain.executor.ThreadExecutor
import com.sorrowbeaver.momo.domain.interactor.GetProfile.Params
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable

class GetProfile (
    val userRepository: UserRepository, threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<User, Params>(threadExecutor, postExecutionThread) {

  data class Params(val userId : Long)

  override fun buildUseCaseObservable(params: Params): Observable<User> {
    return userRepository.detail(params.userId)
  }
}
