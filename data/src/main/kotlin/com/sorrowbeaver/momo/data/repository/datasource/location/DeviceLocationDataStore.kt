package com.sorrowbeaver.momo.data.repository.datasource.map

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationManager
import com.sorrowbeaver.momo.data.AndroidLocation
import com.sorrowbeaver.momo.data.permission.PermissionChecker
import com.sorrowbeaver.momo.data.repository.datasource.location.LocationStream
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class DeviceLocationDataStore @Inject constructor(
  private val permissionChecker: PermissionChecker,
  private val locationManager: LocationManager
) : LocationDataStore {
  @SuppressLint("MissingPermission")
  override fun getCurrentLocation(): Single<Location> {
    return if (permissionChecker.hasLocationPermission()) {
      Maybe.fromCallable {
        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
      }.mergeWith(
        Maybe.fromCallable {
          locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        }
      ).firstElement()
        .switchIfEmpty(
          LocationStream(locationManager, 0L, 0f).firstElement()
        ).toSingle()
    } else {
      //TODO Don't expose data layer exception to other layer
      Single.error(RuntimeException("Location permission not granted"))
    }
  }

  override fun trackLocation(minTime: Long, minDistance: Float): Observable<AndroidLocation> {
    return if (permissionChecker.hasLocationPermission()) {
      LocationStream(locationManager, minTime, minDistance)
    } else {
      //TODO Don't expose data layer exception to other layer
      Observable.error(RuntimeException("Location permission not granted"))
    }
  }
}
