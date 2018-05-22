package com.sorrowbeaver.momo.data.net

import android.content.Context
import android.location.Criteria
import android.location.LocationManager
import android.os.SystemClock
import android.support.test.InstrumentationRegistry
import android.support.test.annotation.UiThreadTest
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.sorrowbeaver.momo.data.AndroidLocation
import com.sorrowbeaver.momo.data.permission.PermissionChecker
import com.sorrowbeaver.momo.data.repository.datasource.location.LocationStream
import com.sorrowbeaver.momo.data.repository.datasource.map.DeviceLocationDataStore
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocationInstrumentedTest {

  private lateinit var locationDataStore: DeviceLocationDataStore
  private lateinit var locationManager: LocationManager
  private val testProviderName = this::class.java.simpleName

  @get:Rule
  var permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
    android.Manifest.permission.ACCESS_FINE_LOCATION
  )

  @Before
  fun setUp() {
    val context = InstrumentationRegistry.getContext()
    val permissionChecker = PermissionChecker(context)
    locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    locationDataStore = DeviceLocationDataStore(permissionChecker, locationManager)

    if (!locationManager.allProviders.contains(testProviderName)) {
      locationManager.addTestProvider(
        testProviderName, false, false,
        false, false, false,
        false, false,
        Criteria.POWER_LOW, Criteria.ACCURACY_FINE
      )
      locationManager.setTestProviderEnabled(testProviderName, true)
    }
  }

  @Test
  @UiThreadTest
  fun testGetCurrentLocation() {
    val location = locationDataStore.getCurrentLocation().blockingGet()
    Assert.assertNotNull(location)
  }

  @Test
  @UiThreadTest
  fun testGetTrack() {
    val androidLocation = AndroidLocation(testProviderName)
    androidLocation.latitude = 1.0
    androidLocation.longitude = 2.0
    androidLocation.accuracy = 500f
    androidLocation.time = System.currentTimeMillis()
    androidLocation.elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos()

    locationManager.setTestProviderLocation(
      testProviderName,
      androidLocation
    )

    val location = LocationStream(
      locationManager, 0L,
      0f, testProviderName
    ).firstElement().blockingGet()

    Assert.assertNotNull(location)
  }

  @After
  fun teatDown() {
    locationManager.removeTestProvider(testProviderName)
  }
}
