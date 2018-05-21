package com.sorrowbeaver.momo.data.net

import android.content.Context
import android.location.LocationManager
import android.support.test.InstrumentationRegistry
import android.support.test.annotation.UiThreadTest
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.sorrowbeaver.momo.data.permission.PermissionChecker
import com.sorrowbeaver.momo.data.repository.datasource.map.DeviceLocationDataStore
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocationInstrumentedTest {

  private lateinit var locationDataStore: DeviceLocationDataStore

  @get:Rule
  var permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
    android.Manifest.permission.ACCESS_FINE_LOCATION
  )

  @Before
  fun setUp() {
    val context = InstrumentationRegistry.getContext()
    val permissionChecker = PermissionChecker(context)
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE)
      as LocationManager
    locationDataStore = DeviceLocationDataStore(permissionChecker, locationManager)
  }

  @Test
  @UiThreadTest
  fun testGetCurrentLocation() {
    val location = locationDataStore.getCurrentLocation().blockingGet()
    Assert.assertNotNull(location)
  }
}