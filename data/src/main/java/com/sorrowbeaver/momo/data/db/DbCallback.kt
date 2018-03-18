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

    db.execSQL("CREATE TABLE pin " +
        "(id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
        "name TEXT, " +
        "pin_label INTEGER, " +
        "created_at INTEGER, " +
        "author_id INTEGER, " +
        "author_name TEXT, " +
        "map INTEGER)"
    )

    db.execSQL("CREATE TABLE pin_posts (pin_id INTEGER, post_id INTEGER)")
  }

  override fun onUpgrade(db: SupportSQLiteDatabase, oldVersion: Int, newVersion: Int) {
  }

}