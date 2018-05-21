package com.sorrowbeaver.momo.data.repository.datasource.map

import com.sorrowbeaver.momo.data.entity.mapper.AndroidLocationDataMapper
import com.sorrowbeaver.momo.domain.model.Location
import com.sorrowbeaver.momo.domain.repository.LocationRepository
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
}
