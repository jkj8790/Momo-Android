package com.sorrowbeaver.momo.data.di

import android.app.Application
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory
import android.content.Context
import android.location.LocationManager
import android.util.Log
import com.sorrowbeaver.momo.data.db.DbCallback
import com.sorrowbeaver.momo.data.permission.PermissionChecker
import com.sorrowbeaver.momo.data.repository.datasource.map.DeviceLocationDataStore
import com.sorrowbeaver.momo.data.repository.datasource.map.DiskMapDataStore
import com.sorrowbeaver.momo.data.repository.datasource.map.LocationDataStore
import com.sorrowbeaver.momo.data.repository.datasource.map.MapDataStore
import com.sorrowbeaver.momo.data.repository.datasource.pin.DiskPinDataStore
import com.sorrowbeaver.momo.data.repository.datasource.pin.PinDataStore
import com.sorrowbeaver.momo.data.repository.datasource.post.DiskPostDataStore
import com.sorrowbeaver.momo.data.repository.datasource.post.PostDataStore
import com.sorrowbeaver.momo.data.repository.datasource.search.FakeSearchDataStore
import com.sorrowbeaver.momo.data.repository.datasource.search.SearchDataStore
import com.sorrowbeaver.momo.data.repository.datasource.user.FakeUserDataStore
import com.sorrowbeaver.momo.data.repository.datasource.user.UserDataStore
import com.squareup.sqlbrite3.BriteDatabase
import com.squareup.sqlbrite3.SqlBrite
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

const val DATABASE_NAME = "momo.db"

@Singleton
@Module
class DatabaseModule(private val application: Application) {

  @Provides
  fun provideSqlBrite() = SqlBrite.Builder()
    .logger { Log.d("Database", it) }
    .build()!!

  @Provides
  fun briteDataBase(sqlBrite: SqlBrite): BriteDatabase {
    val configuration = Configuration.builder(application)
      .name(DATABASE_NAME)
      .callback(
        DbCallback(application)
      )
      .build()
    val factory = FrameworkSQLiteOpenHelperFactory()
    val helper = factory.create(configuration)
    val db = sqlBrite.wrapDatabaseHelper(helper, Schedulers.io())
    db.setLoggingEnabled(true)
    return db
  }

  @Provides
  fun permissionChecker() = PermissionChecker(application)

  @Provides
  fun locationManager(): LocationManager {
    return application.getSystemService(Context.LOCATION_SERVICE)
      as LocationManager
  }

  @Provides
  fun pinDataStore(db: BriteDatabase): PinDataStore = DiskPinDataStore(db)

  @Provides
  fun mapDataStore(db: BriteDatabase): MapDataStore = DiskMapDataStore(db)

  @Provides
  fun postDataStore(db: BriteDatabase): PostDataStore = DiskPostDataStore(db)

  @Provides
  fun searchDataStore(): SearchDataStore = FakeSearchDataStore()

  @Provides
  fun userDataStore(): UserDataStore = FakeUserDataStore()

  @Provides
  fun locationDataStore(
    permissionChecker: PermissionChecker,
    locationManager: LocationManager
  ): LocationDataStore {
    return DeviceLocationDataStore(permissionChecker, locationManager)
  }
}
