package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.Post
import com.sorrowbeaver.momo.domain.repository.PostRepository
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
import io.reactivex.Observable

class GetPostsByMapId(
  private val postRepository: PostRepository
) : UseCase<List<Post>, GetPostsByMapId.Params> {

  data class Params(val mapId: Long)

  override fun execute(params: Params): Observable<List<Post>> {
    return postRepository.getPostsByMapId(params.mapId)
  }
}
