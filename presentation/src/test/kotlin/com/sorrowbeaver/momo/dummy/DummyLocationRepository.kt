package com.sorrowbeaver.momo.dummy

import com.sorrowbeaver.momo.domain.model.Location
import com.sorrowbeaver.momo.domain.repository.LocationRepository
import io.reactivex.Observable
import io.reactivex.Single

object DummyLocationRepository : LocationRepository {
  override fun trackLocation(minTimeInterval: Long, minDistance: Float): Observable<Location> {
    throw NotImplementedError()
  }

  override fun getCurrentLocation(): Single<Location> {
    throw NotImplementedError()
  }
}