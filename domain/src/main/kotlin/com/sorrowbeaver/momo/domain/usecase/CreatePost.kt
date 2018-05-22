package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.Post
import com.sorrowbeaver.momo.domain.repository.PostRepository
import com.sorrowbeaver.momo.domain.usecase.CreatePost.Params
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
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
