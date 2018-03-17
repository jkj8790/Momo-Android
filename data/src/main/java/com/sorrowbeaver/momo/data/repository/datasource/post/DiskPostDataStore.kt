package com.sorrowbeaver.momo.data.repository.datasource.post

import com.sorrowbeaver.momo.data.entity.PostEntity
import com.squareup.sqlbrite3.BriteDatabase
import io.reactivex.Observable
import java.util.Date

class DiskPostDataStore(
  private val db: BriteDatabase
): PostDataStore {

  override fun posts(): Observable<List<PostEntity>> {
    return db.createQuery("posts", "SELECT * FROM posts")
        .mapToList { PostEntity(
            it.getLong(0), it.getLong(1), it.getString(2),
            it.getString(3), Date(it.getLong(4))
        ) }
  }

  override fun createPost(pinId: Long, photoUrl: String?,
      description: String?): Observable<PostEntity> {

    TODO("not implemented")
  }

  override fun detail(id: Long): Observable<PostEntity> {
    TODO("not implemented")
  }

}