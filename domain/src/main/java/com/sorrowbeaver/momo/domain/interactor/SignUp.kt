package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.executor.PostExecutionThread
import com.sorrowbeaver.momo.domain.executor.ThreadExecutor
import com.sorrowbeaver.momo.domain.interactor.SignUp.Params
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable

class SignUp (
    val userRepository: UserRepository, threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<User, Params>(threadExecutor, postExecutionThread) {

  data class Params(val email: String, val id: String, val password: String)

  override fun buildUseCaseObservable(params: Params): Observable<User> {
    return userRepository.signUp(params.email, params.id, params.password)
  }
}
