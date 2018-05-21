package com.sorrowbeaver.momo.data.repository.datasource.map

import android.location.Location
import io.reactivex.Single

interface LocationDataStore {

  fun getCurrentLocation(): Single<Location>
}
