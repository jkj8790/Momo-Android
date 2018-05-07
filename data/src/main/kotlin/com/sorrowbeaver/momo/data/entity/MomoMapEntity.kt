package com.sorrowbeaver.momo.data.entity

import android.database.Cursor
import java.util.Date

data class MomoMapEntity(
  val pk: Long,
  val map_name: String,
  val description: String,
  val is_private: Boolean,
  val authorId: Long,
  val createdDate: Date,
  val pinIds: List<Long>
) {
  constructor(cursor: Cursor, pinIds: List<Long>) : this(
    cursor.getLong(0),
    cursor.getString(1),
    cursor.getString(2),
    cursor.getInt(3) == 1,
    cursor.getLong(4),
    Date(cursor.getLong(5)),
    pinIds
  )
}
