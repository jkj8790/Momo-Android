package com.sorrowbeaver.momo.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sorrowbeaver.momo.MomoApplication
import com.sorrowbeaver.momo.R.color
import com.sorrowbeaver.momo.R.id
import com.sorrowbeaver.momo.R.layout
import com.sorrowbeaver.momo.map.MapFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.nav_header.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnMapReadyCallback, MainContract.View {

  private var mMap: GoogleMap? = null
  @Inject
  lateinit var presenter: MainPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (application as MomoApplication).component.inject(this)
    setContentView(layout.activity_maps)

    // Set up the toolbar.
    setSupportActionBar(toolbar)
    val ab = supportActionBar
    ab?.setHomeAsUpIndicator(android.R.drawable.ic_menu_edit)
    ab?.setDisplayHomeAsUpEnabled(true)

    // Set up the navigation drawer.
    drawerLayout.setStatusBarBackground(color.colorPrimaryDark)
    setupDrawerContent(navigationView)

    var mapFragment = supportFragmentManager.findFragmentById(
      id.contentFrame
    ) as? MapFragment
    if (mapFragment == null) {
      // Obtain the SupportMapFragment and get notified when the map is ready to be used.
      // Create the fragment
      mapFragment = MapFragment()
      val transaction = supportFragmentManager.beginTransaction()
      transaction.add(id.contentFrame, mapFragment)
      transaction.commit()
    }

    mapFragment.getMapAsync(this)

    // Load previously saved state, if available.
    if (savedInstanceState != null) {
    }

    toolbar.setNavigationOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }

    presenter.takeView(this)
  }

  override fun onResume() {
    super.onResume()
    presenter.subscribe()
  }

  override fun onPause() {
    super.onPause()
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    return super.onOptionsItemSelected(item)
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
        id.list_navigation_menu_item -> {
          Toast.makeText(this, "a", Toast.LENGTH_SHORT).show()
        }
        else -> {
        }
      } // Do nothing, we're already on that screen
      // Close the navigation drawer when an item is selected.
      menuItem.isChecked = true
      drawerLayout.closeDrawers()
      true
    }
  }

  override fun showLoading() {
    // TODO show profile dialog
  }

  override fun hideLoading() {
    // TODO hide profile dialog
  }

  override fun showError() {
    Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
  }

  override fun showProfileImage(profileUrl: String) {
    Picasso.with(this).load(profileUrl).into(imgProfile)
  }

  override fun showUserName(userName: String) {
    textUserName.text = userName
  }
}
