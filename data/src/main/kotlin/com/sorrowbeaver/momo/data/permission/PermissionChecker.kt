package com.sorrowbeaver.momo.data.permission

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.ContextCompat
import javax.inject.Inject

class PermissionChecker @Inject constructor(
  private val context: Context
) {

  private fun hasLocationPermission(): Boolean {
    val finePermission = ContextCompat.checkSelfPermission(
      context, android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    val coarsePermission = ContextCompat.checkSelfPermission(
      context, android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    return (Build.VERSION.SDK_INT > Build.VERSION_CODES.M
      && finePermission == PackageManager.PERMISSION_GRANTED
      && coarsePermission == PackageManager.PERMISSION_GRANTED)
  }
}