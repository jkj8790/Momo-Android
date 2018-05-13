package com.sorrowbeaver.momo.data.repository.datasource.post

import android.arch.persistence.db.SimpleSQLiteQuery
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.sorrowbeaver.momo.data.entity.PostEntity
import com.squareup.sqlbrite3.BriteDatabase
import io.reactivex.Observable
import java.util.Date

class DiskPostDataStore(
  private val db: BriteDatabase
) : PostDataStore {

  override fun posts(): Observable<List<PostEntity>> {
    return db.createQuery("posts", "SELECT * FROM posts")
      .mapToList { PostEntity(it) }
  }

  override fun createPost(
    pinId: Long,
    photoUrl: String?,
    description: String?
  ): Observable<PostEntity> {

    return Observable.fromCallable {
      db.insert(
        "posts",
        SQLiteDatabase.CONFLICT_REPLACE,
        createContentValues(pinId, photoUrl, description)
      )
    }
      .flatMap { detail(it) }
  }

  override fun detail(id: Long): Observable<PostEntity> {
    return db.createQuery("posts", "SELECT * FROM posts WHERE id = $id")
      .mapToOne { PostEntity(it) }
  }

  override fun getPostsByPinId(pinId: Long): Observable<List<PostEntity>> {
    return db.createQuery("posts", "SELECT * FROM posts WHERE pin_id = $pinId")
      .mapToList { PostEntity(it) }
  }

  override fun getPostsByMapId(mapId: Long): Observable<List<PostEntity>> {
    val pinIds = db.createQuery(
      "map_pins",
      SimpleSQLiteQuery(
        "SELECT pin_id FROM map_pins where map_id = ?",
        arrayOf(mapId)
      )
    ).mapToList { it.getLong(it.getColumnIndex("pin_id")) }
      .blockingFirst()
    return db.createQuery(
      "posts", "SELECT * FROM posts WHERE pin_id = ?",
      pinIds.joinToString(prefix = "IN(", postfix = ")")
    )
      .mapToList { PostEntity(it) }
  }

  private fun createContentValues(
    pinId: Long,
    photoUrl: String?,
    description: String?
  ): ContentValues {
    return ContentValues().apply {
      put("pin_id", pinId)
      photoUrl?.let { put("photo_url", it) }
      description?.let { put("description", it) }
      put("created_at", Date().time)
    }
  }
}
