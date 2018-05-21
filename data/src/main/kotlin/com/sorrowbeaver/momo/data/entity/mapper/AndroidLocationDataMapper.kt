package com.sorrowbeaver.momo.data.entity.mapper

import com.sorrowbeaver.momo.data.AndroidLocation
import com.sorrowbeaver.momo.domain.model.Location
import javax.inject.Inject

class AndroidLocationDataMapper @Inject constructor() {

  fun map(androidLocation: AndroidLocation): Location {
    return Location(
      androidLocation.latitude,
      androidLocation.longitude
    )
  }
}