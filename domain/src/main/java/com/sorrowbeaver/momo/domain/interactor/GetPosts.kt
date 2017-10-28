package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.model.Post
import com.sorrowbeaver.momo.domain.repository.PostRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetPosts (
    val postRepository: PostRepository,
    executorScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<List<Post>, Unit>(executorScheduler, postExecutionScheduler) {

  override fun buildObservable(params: Unit): Observable<List<Post>> {
    return postRepository.posts()
  }
}
