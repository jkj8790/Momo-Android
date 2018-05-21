package com.sorrowbeaver.momo.data.repository.datasource.location

import android.annotation.SuppressLint
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import com.sorrowbeaver.momo.data.AndroidLocation
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable
import io.reactivex.disposables.Disposables

class LocationStream(
  private val locationManager: LocationManager,
  private val minTime: Long,
  private val minDistance: Float
) : Observable<AndroidLocation>() {
  @SuppressLint("MissingPermission")
  override fun subscribeActual(observer: Observer<in AndroidLocation>) {
    if (Looper.myLooper() != Looper.getMainLooper()) {
      observer.onSubscribe(Disposables.empty())
      observer.onError(
        IllegalStateException(
          "Expected to be called on the main thread but was "
            + Thread.currentThread().name
        )
      )
      return
    }

    val listener = Listener(locationManager, observer)
    observer.onSubscribe(listener)
    locationManager.requestLocationUpdates(
      LocationManager.GPS_PROVIDER, minTime, minDistance, listener
    )
  }

  class Listener(
    private val locationManager: LocationManager,
    private val observer: Observer<in AndroidLocation>
  ) : MainThreadDisposable(), LocationListener {
    override fun onLocationChanged(location: AndroidLocation?) {
      if (!isDisposed && location != null) {
        observer.onNext(location)
      }
    }

    override fun onDispose() {
      locationManager.removeUpdates(this)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }
  }
}