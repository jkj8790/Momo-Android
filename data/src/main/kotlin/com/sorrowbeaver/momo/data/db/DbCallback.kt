package com.sorrowbeaver.momo.data.db

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.db.SupportSQLiteOpenHelper
import com.sorrowbeaver.momo.data.R

const val DATABASE_VERSION = 1

// TODO consider using ORM library
class DbCallback(
  val application: Application
) : SupportSQLiteOpenHelper.Callback(DATABASE_VERSION) {

  override fun onCreate(db: SupportSQLiteDatabase) {
    application.resources.openRawResource(R.raw.create).bufferedReader().use {
      it.readText()
    }.let {
      db.execSQL(it)
    }
  }

  override fun onUpgrade(db: SupportSQLiteDatabase, oldVersion: Int, newVersion: Int) {
  }
}
