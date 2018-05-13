package com.sorrowbeaver.momo.data.repository.datasource.post

import com.sorrowbeaver.momo.data.entity.mapper.PostEntityDataMapper
import com.sorrowbeaver.momo.domain.model.Post
import com.sorrowbeaver.momo.domain.repository.PostRepository
import io.reactivex.Observable
import javax.inject.Inject

class PostDataRepository @Inject constructor(
  private val postDataStore: PostDataStore,
  private val postEntityDataMapper: PostEntityDataMapper
) : PostRepository {

  override fun getAllPosts(): Observable<List<Post>> {
    return postDataStore.posts()
      .map { postEntityDataMapper.transform(it) }
  }

  override fun createPost(pinId: Long, photoUrl: String?, description: String?): Observable<Post> {
    return postDataStore.createPost(pinId, photoUrl, description)
      .map { postEntityDataMapper.transform(it) }
  }

  override fun detail(id: Long): Observable<Post> {
    return postDataStore.detail(id)
      .map { postEntityDataMapper.transform(it) }
  }

  override fun getPostsByPinId(pinId: Long): Observable<List<Post>> {
    return postDataStore.getPostsByPinId(pinId)
      .map { postEntityDataMapper.transform(it) }
  }

  override fun getPostsByMapId(mapId: Long): Observable<List<Post>> {
    return postDataStore.getPostsByMapId(mapId)
      .map { postEntityDataMapper.transform(it) }
  }
}
