package com.sorrowbeaver.momo.domain.repository

import com.sorrowbeaver.momo.domain.model.Location
import io.reactivex.Observable
import io.reactivex.Single

interface LocationRepository {

  fun getCurrentLocation(): Single<Location>

  fun trackLocation(
    minTimeInterval: Long,
    minDistance: Float
  ): Observable<Location>
}
