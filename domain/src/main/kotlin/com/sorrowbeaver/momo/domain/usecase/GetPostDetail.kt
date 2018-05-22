package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.Post
import com.sorrowbeaver.momo.domain.repository.PostRepository
import com.sorrowbeaver.momo.domain.usecase.GetPostDetail.Params
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
import io.reactivex.Observable

class GetPostDetail(
  private val postRepository: PostRepository
) : UseCase<Post, Params> {

  data class Params(val id: Long)

  override fun execute(params: Params): Observable<Post> {
    return postRepository.detail(params.id)
  }
}
