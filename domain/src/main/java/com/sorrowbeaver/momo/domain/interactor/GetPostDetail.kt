package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.GetPostDetail.Params
import com.sorrowbeaver.momo.domain.model.Post
import com.sorrowbeaver.momo.domain.repository.PostRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetPostDetail (
    val postRepository: PostRepository,
    executorScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<Post, Params>(executorScheduler, postExecutionScheduler) {

  data class Params(val id: Long)

  override fun buildObservable(params: Params): Observable<Post> {
    return postRepository.detail(params.id)
  }
}
