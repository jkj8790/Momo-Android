package com.sorrowbeaver.momo.data.repository.datasource.post

import com.sorrowbeaver.momo.data.entity.mapper.PostEntityDataMapper
import com.sorrowbeaver.momo.domain.model.Post
import com.sorrowbeaver.momo.domain.repository.PostRepository
import io.reactivex.Observable

class PostDataRepository : PostRepository {

  val postDataStore = PostDataStoreFactory().create()
  val postDataEntityDataMapper = PostEntityDataMapper()

  override fun posts(): Observable<List<Post>> {
    return postDataStore.posts()
        .map { postDataEntityDataMapper.transform(it) }
  }

  override fun createPost(pinId: Long, photoUrl: String?, description: String?): Observable<Post> {
    return postDataStore.createPost(pinId, photoUrl, description)
        .map { postDataEntityDataMapper.transform(it) }
  }

  override fun detail(id: Long): Observable<Post> {
    return postDataStore.detail(id)
        .map { postDataEntityDataMapper.transform(it) }
  }

}
