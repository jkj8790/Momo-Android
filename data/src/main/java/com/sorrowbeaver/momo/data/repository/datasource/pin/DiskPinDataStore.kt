package com.sorrowbeaver.momo.data.repository.datasource.pin

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.sorrowbeaver.momo.data.entity.PinEntity
import com.squareup.sqlbrite3.BriteDatabase
import io.reactivex.Observable
import io.reactivex.rxkotlin.zipWith
import java.util.Date
import javax.inject.Inject

class DiskPinDataStore @Inject constructor(
  private val db: BriteDatabase
) : PinDataStore {

  override fun pins(): Observable<List<PinEntity>> {
    return db.createQuery("pin", "SELECT * FROM pin")
      .mapToList { PinEntity(it, emptyList()) }
  }

  override fun createPin(
    name: String,
    pinLabel: Int,
    authorId: Long,
    authorName: String,
    momoMapId: Long
  ): Observable<PinEntity> {
    val id = db.insert(
      "pin", SQLiteDatabase.CONFLICT_REPLACE,
      createContentValues(name, pinLabel, authorId, authorName, momoMapId)
    )

    return detail(id)
  }

  override fun detail(id: Long): Observable<PinEntity> {
    val posts = db.createQuery(
      "pin_posts", "SELECT post_id FROM pin_posts where pin_id = $id"
    )
      .mapToList { it.getLong(it.getColumnIndex("post_id")) }

    return db.createQuery("pin", "SELECT * FROM pin WHERE id = $id")
      .mapToOne { it }
      .zipWith(posts) { cursor: Cursor, postIds: List<Long> ->
        PinEntity(cursor, postIds)
      }
  }

  private fun createContentValues(
    name: String,
    pinLabel: Int,
    authorId: Long,
    authorName: String,
    momoMapId: Long
  ): ContentValues {
    return ContentValues().apply {
      put("name", name)
      put("pin_label", pinLabel)
      put("created_at", Date().time)
      put("author_id", authorId)
      put("author_name", authorName)
      put("map", momoMapId)
    }
  }
}
