package com.sorrowbeaver.momo.data.repository.datasource.post

import com.sorrowbeaver.momo.data.entity.PostEntity
import io.reactivex.Observable
import java.util.Date

class FakePostDataStore : PostDataStore {

  val FAKE_POST = PostEntity(
      0, 0, "photho", "description", Date()
  )

  override fun posts(): Observable<List<PostEntity>> {
    return Observable.just(listOf(FAKE_POST))
  }

  override fun createPost(pinId: Long, photoUrl: String?,
      description: String?): Observable<PostEntity> {
    return Observable.just(FAKE_POST)
  }

  override fun detail(id: Long): Observable<PostEntity> {
    return Observable.just(FAKE_POST)
  }

}
