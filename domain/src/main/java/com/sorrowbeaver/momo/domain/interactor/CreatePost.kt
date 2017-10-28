package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.CreatePost.Params
import com.sorrowbeaver.momo.domain.model.Post
import com.sorrowbeaver.momo.domain.repository.PostRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class CreatePost (
    val postRepository: PostRepository,
    executorScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<Post, Params>(executorScheduler, postExecutionScheduler) {

  data class Params(val pinId: Long, val photoUrl: String?, val description: String?)

  override fun buildObservable(params: Params): Observable<Post> {
    return postRepository.createPost(params.pinId,
        params.photoUrl, params.description)
  }
}
