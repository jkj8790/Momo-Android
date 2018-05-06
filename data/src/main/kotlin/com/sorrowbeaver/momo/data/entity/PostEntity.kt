package com.sorrowbeaver.momo.data.entity

import android.database.Cursor
import java.util.Date

data class PostEntity(
  val pk: Long,
  val pin: Long,
  val photo: String,
  val description: String,
  val created_date: Date
) {
  constructor(cursor: Cursor) : this(
    cursor.getLong(0),
    cursor.getLong(1), cursor.getString(2),
    cursor.getString(3), Date(cursor.getLong(4))
  )
}
