package com.sorrowbeaver.momo.data.entity

import android.database.Cursor
import java.util.Date

data class MomoMapEntity(
  val pk: Long,
  val map_name: String,
  val description: String,
  val is_private: Boolean,
  val authorId: Long,
  val authorName: String,
  var pins: List<PinEntity>,
  val createdDate: Date
) {
  constructor(cursor: Cursor) : this(
    cursor.getLong(0),
    cursor.getString(1),
    cursor.getString(2),
    cursor.getInt(3) == 1,
    cursor.getLong(4),
    cursor.getString(5),
    emptyList(),
    Date(cursor.getLong(6))
  )
}
