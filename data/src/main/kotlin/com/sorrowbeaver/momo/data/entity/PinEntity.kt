package com.sorrowbeaver.momo.data.entity

import android.database.Cursor
import java.util.Date

data class PinEntity(
  val id: Long,
  val name: String,
  val pinLabel: Int,
  // 0 -> Place, 1 -> Food, 2 -> Cafe, 3 -> Shop, 4 -> etc
  val createdAt: Date,
  val authorId: Long,
  val authorName: String,
  val mapId: Long,
  val postIds: List<Long>
) {
  constructor(
    cursor: Cursor,
    postIds: List<Long>
  ) : this(
    cursor.getLong(0),
    cursor.getString(1),
    cursor.getInt(2),
    Date(cursor.getLong(3)),
    cursor.getLong(4),
    cursor.getString(5),
    cursor.getLong(6),
    postIds
  )
}
