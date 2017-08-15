package com.sorrowbeaver.momo

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.drawerLayout
import kotlinx.android.synthetic.main.activity_maps.navigationView
import kotlinx.android.synthetic.main.activity_maps.toolbar

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

  private var mMap: GoogleMap? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_maps)

    // Set up the toolbar.
    setSupportActionBar(toolbar)
    val ab = supportActionBar
    ab?.setHomeAsUpIndicator(android.R.drawable.ic_menu_edit)
    ab?.setDisplayHomeAsUpEnabled(true)

    // Set up the navigation drawer.
    drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark)
    setupDrawerContent(navigationView)

    var supportMapFragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as?
        SupportMapFragment
    if (supportMapFragment == null) {
      // Obtain the SupportMapFragment and get notified when the map is ready to be used.
      // Create the fragment
      supportMapFragment = SupportMapFragment()
      val transaction = supportFragmentManager.beginTransaction()
      transaction.add(R.id.contentFrame, supportMapFragment)
      transaction.commit()
    }

    supportMapFragment.getMapAsync(this)

    // Load previously saved state, if available.
    if (savedInstanceState != null) {
    }
  }


  /**
   * Manipulates the map once available.
   * This callback is triggered when the map is ready to be used.
   * This is where we can add markers or lines, add listeners or move the camera. In this case,
   * we just add a marker near Sydney, Australia.
   * If Google Play services is not installed on the device, the user will be prompted to install
   * it inside the SupportMapFragment. This method will only be triggered once the user has
   * installed Google Play services and returned to the app.
   */
  override fun onMapReady(googleMap: GoogleMap) {
    mMap = googleMap

    // Add a marker in Sydney and move the camera
    val sydney = LatLng(-34.0, 151.0)
    mMap!!.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
    mMap!!.moveCamera(CameraUpdateFactory.newLatLng(sydney))
  }

  private fun setupDrawerContent(navigationView: NavigationView) {
    navigationView.setNavigationItemSelectedListener { menuItem ->
      when (menuItem.itemId) {
        R.id.list_navigation_menu_item -> {
        }
        else -> {
        }
      }// Do nothing, we're already on that screen
      // Close the navigation drawer when an item is selected.
      menuItem.isChecked = true
      drawerLayout.closeDrawers()
      true
    }
  }
}
