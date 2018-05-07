package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.Post
import com.sorrowbeaver.momo.domain.repository.PostRepository
import io.reactivex.Observable

class GetAllPosts(
  private val postRepository: PostRepository
) : UseCase<List<Post>, Unit> {

  override fun execute(params: Unit): Observable<List<Post>> {
    return postRepository.getAllPosts()
  }
}
