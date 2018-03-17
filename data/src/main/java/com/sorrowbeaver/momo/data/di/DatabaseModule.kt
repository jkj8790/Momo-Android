package com.sorrowbeaver.momo.data.di

import android.app.Application
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory
import android.util.Log
import com.sorrowbeaver.momo.data.db.DbCallback
import com.squareup.sqlbrite3.BriteDatabase
import com.squareup.sqlbrite3.SqlBrite
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

const val DATABASE_NAME = "momo.db"

@Singleton
@Module
class DatabaseModule {

  @Provides
  fun provideSqlBrite() = SqlBrite.Builder()
      .logger { Log.d("Database", it) }
      .build()!!

  @Provides
  fun briteDataBase(sqlBrite: SqlBrite, application: Application)
  : BriteDatabase{
    val configuration = Configuration.builder(application)
        .name(DATABASE_NAME)
        .callback(DbCallback())
        .build()
    val factory = FrameworkSQLiteOpenHelperFactory()
    val helper = factory.create(configuration)
    val db = sqlBrite.wrapDatabaseHelper(helper, Schedulers.io())
    db.setLoggingEnabled(true)
    return db
  }

}