package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.CreatePost.Params
import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.Post
import com.sorrowbeaver.momo.domain.repository.PostRepository
import io.reactivex.Observable

class CreatePost(
  private val postRepository: PostRepository
) : UseCase<Post, Params> {

  data class Params(val pinId: Long, val photoUrl: String?, val description: String?)

  override fun execute(params: Params): Observable<Post> {
    return postRepository.createPost(
      params.pinId,
      params.photoUrl, params.description
    )
  }
}
