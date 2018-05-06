package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.GetPostDetail.Params
import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.Post
import com.sorrowbeaver.momo.domain.repository.PostRepository
import io.reactivex.Observable

class GetPostDetail(
  private val postRepository: PostRepository
) : UseCase<Post, Params> {

  data class Params(val id: Long)

  override fun execute(params: Params): Observable<Post> {
    return postRepository.detail(params.id)
  }
}
