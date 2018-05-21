package com.sorrowbeaver.momo.data.repository.datasource.map

import com.sorrowbeaver.momo.data.entity.mapper.AndroidLocationDataMapper
import com.sorrowbeaver.momo.domain.model.Location
import com.sorrowbeaver.momo.domain.repository.LocationRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class LocationDataRepository @Inject constructor(
  private val dataStore: LocationDataStore,
  private val mapper: AndroidLocationDataMapper
) : LocationRepository {
  override fun getCurrentLocation(): Single<Location> {
    return dataStore.getCurrentLocation()
      .map { mapper.map(it) }
  }

  override fun trackLocation(
    minTimeInterval: Long,
    minDistance: Float
  ): Observable<Location> {
    return dataStore.trackLocation(minTimeInterval, minDistance)
      .map { mapper.map(it) }
  }
}
