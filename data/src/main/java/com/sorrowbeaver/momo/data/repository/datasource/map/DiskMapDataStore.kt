package com.sorrowbeaver.momo.data.repository.datasource.map

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.sorrowbeaver.momo.data.entity.MomoMapEntity
import com.squareup.sqlbrite3.BriteDatabase
import io.reactivex.Observable
import io.reactivex.rxkotlin.zipWith
import java.util.Date
import javax.inject.Inject

class DiskMapDataStore @Inject constructor(
  private val db: BriteDatabase
) : MapDataStore {

  override fun createMap(
    name: String,
    description: String,
    isPrivate: Boolean,
    authorId: Long,
    authorName: String
  ): Observable<MomoMapEntity> {
    val id = db.insert(
      "map", SQLiteDatabase.CONFLICT_REPLACE,
      createContentValues(name, description, isPrivate, authorId, authorName)
    )

    return detail(id)
  }

  override fun maps(): Observable<List<MomoMapEntity>> {
    return db.createQuery("map", "SELECT * FROM map")
      .mapToList { MomoMapEntity(it, emptyList()) }
  }

  override fun detail(id: Long): Observable<MomoMapEntity> {
    val pinIdQuery = db.createQuery(
      "map_pins", "SELECT pin_id FROM map_pins where pin_id = $id"
    )
      .mapToList { it.getLong(it.getColumnIndex("pin_id")) }

    return db.createQuery("map", "SELECT * FROM map WHERE id = $id")
      .mapToOne { it }
      .zipWith(pinIdQuery) { cursor: Cursor, pinIds: List<Long> ->
        MomoMapEntity(cursor, pinIds)
      }
  }

  private fun createContentValues(
    name: String,
    description: String,
    isPrivate: Boolean,
    authorId: Long,
    authorName: String
  ): ContentValues {
    return ContentValues().apply {
      put("name", name)
      put("description", description)
      put("is_private", if (isPrivate) 1 else 0)
      put("author_id", authorId)
      put("author_name", authorName)
      put("created_at", Date().time)
    }
  }
}
