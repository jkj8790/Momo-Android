package com.sorrowbeaver.momo.data.repository.datasource.map

import android.arch.persistence.db.SimpleSQLiteQuery
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.sorrowbeaver.momo.data.entity.MomoMapEntity
import com.squareup.sqlbrite3.BriteDatabase
import io.reactivex.Observable
import java.util.Date
import javax.inject.Inject

class DiskMapDataStore @Inject constructor(
  private val db: BriteDatabase
) : MapDataStore {

  override fun createMap(
    name: String,
    description: String,
    isPrivate: Boolean,
    authorId: Long
  ): Observable<MomoMapEntity> {
    val id = db.insert(
      "map", SQLiteDatabase.CONFLICT_REPLACE,
      createContentValues(name, description, isPrivate, authorId)
    )

    return detail(id)
  }

  override fun getAllMaps(): Observable<List<MomoMapEntity>> {
    return db.createQuery("map", "SELECT * FROM map")
      .mapToList { MomoMapEntity(it, emptyList()) }
  }

  override fun getMapsByUserId(userId: Long): Observable<List<MomoMapEntity>> {
    return Observable.fromCallable {
      val mapCursor = db.query(
        "SELECT * FROM map WHERE author_id = ? and is_private = 0",
        userId
      )
      generateSequence { if (mapCursor.moveToNext()) mapCursor else null }
        .map {

          val mapId = mapCursor.getLong(mapCursor.getColumnIndex("id"))
          val pinIdQuery = db.createQuery(
            "map_pins",
            SimpleSQLiteQuery(
              "SELECT pin_id FROM map_pins where map_id = ?",
              arrayOf(mapId)
            )
          ).mapToList { it.getLong(it.getColumnIndex("pin_id")) }

          MomoMapEntity(mapCursor, pinIdQuery.blockingFirst())
        }
        .toList()
    }
  }

  override fun detail(id: Long): Observable<MomoMapEntity> {
    val pinIdQuery = db.createQuery(
      "map_pins",
      SimpleSQLiteQuery(
        "SELECT pin_id FROM map_pins where map_id = ?",
        arrayOf(id)
      )
    ).mapToList { it.getLong(it.getColumnIndex("pin_id")) }

    val query = SimpleSQLiteQuery(
      "SELECT * FROM map where id = ?",
      arrayOf(id)
    )

    return pinIdQuery.flatMap { pinIds ->
      db.createQuery("map", query).mapToOne { MomoMapEntity(it, pinIds) }
    }
  }

  private fun createContentValues(
    name: String,
    description: String,
    isPrivate: Boolean,
    authorId: Long
  ): ContentValues {
    return ContentValues().apply {
      put("name", name)
      put("description", description)
      put("is_private", if (isPrivate) 1 else 0)
      put("author_id", authorId)
      put("created_at", Date().time)
    }
  }
}
