package com.sorrowbeaver.momo.data.repository.datasource.post

import com.sorrowbeaver.momo.data.entity.PostEntity
import io.reactivex.Observable
import java.util.Date
import javax.inject.Inject

class FakePostDataStore @Inject constructor() : PostDataStore {

  private val fakePost = PostEntity(
    0, 0, "photo", "description", Date()
  )

  override fun posts(): Observable<List<PostEntity>> {
    return Observable.just(listOf(fakePost))
  }

  override fun createPost(
    pinId: Long,
    photoUrl: String?,
    description: String?
  ): Observable<PostEntity> {
    return Observable.just(fakePost)
  }

  override fun detail(id: Long): Observable<PostEntity> {
    return Observable.just(fakePost)
  }
}
