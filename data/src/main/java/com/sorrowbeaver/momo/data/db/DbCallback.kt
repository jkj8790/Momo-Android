package com.sorrowbeaver.momo.data.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.db.SupportSQLiteOpenHelper

const val DATABASE_VERSION = 1

// TODO consider using ORM library
class DbCallback : SupportSQLiteOpenHelper.Callback(DATABASE_VERSION) {

  override fun onCreate(db: SupportSQLiteDatabase) {
    db.execSQL("CREATE TABLE posts (id INTEGER PRIMARY KEY, pin_id INTEGER, " +
        "photo_url TEXT, description TEXT, created_at INTEGER)"
    )
  }

  override fun onUpgrade(db: SupportSQLiteDatabase, oldVersion: Int, newVersion: Int) {
  }

}