package com.sorrowbeaver.momo.mapper

import com.sorrowbeaver.momo.domain.model.Location
import com.sorrowbeaver.momo.model.LocationModel
import javax.inject.Inject

open class LocationModelDataMapper @Inject constructor() {
  open fun transform(location: Location) = LocationModel(
    location.latitude,
    location.longitude
  )
}
