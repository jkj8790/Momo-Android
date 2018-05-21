package com.sorrowbeaver.momo.data.repository.datasource.map

import com.sorrowbeaver.momo.data.AndroidLocation
import io.reactivex.Observable
import io.reactivex.Single

interface LocationDataStore {

  fun getCurrentLocation(): Single<AndroidLocation>

  fun trackLocation(
    minTime: Long,
    minDistance: Float
  ): Observable<AndroidLocation>
}
