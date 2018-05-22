package com.sorrowbeaver.momo.main

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.ContextThemeWrapper
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.sorrowbeaver.momo.MomoApplication
import com.sorrowbeaver.momo.R
import com.sorrowbeaver.momo.R.color
import com.sorrowbeaver.momo.R.id
import com.sorrowbeaver.momo.R.layout
import com.sorrowbeaver.momo.domain.model.Location
import com.sorrowbeaver.momo.map.MapFragment
import com.sorrowbeaver.momo.map.create.CreateMapActivity
import com.sorrowbeaver.momo.model.MomoMapModel
import com.squareup.picasso.Picasso
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.nav_header.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnMapReadyCallback, MainContract.View {

  private var googleMap: GoogleMap? = null
  @Inject
  lateinit var presenter: MainPresenter

  private lateinit var arrayAdapter: ArrayAdapter<String>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (application as MomoApplication).component
      .mainComponent()
      .mainModule(MainModule(this))
      .build()
      .inject(this)

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

    fab_add_task.setOnClickListener {
      startActivity(Intent(this, CreateMapActivity::class.java))
    }

    arrayAdapter = ArrayAdapter<String>(
      ContextThemeWrapper(this, R.style.Base_ThemeOverlay_AppCompat_Dark),
      android.R.layout.simple_spinner_item,
      mutableListOf()
    )

    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    val rxPermissions = RxPermissions(this)
    rxPermissions
      .request(Manifest.permission.ACCESS_FINE_LOCATION)
      .subscribe({ granted ->
        if (granted) {
          presenter.loadCurrentLocation()
        }
      })
  }

  override fun moveToCurrentLocation(location: Location) {
    googleMap?.moveCamera(
      CameraUpdateFactory.newLatLngZoom(
        LatLng(
          location.latitude,
          location.longitude
        ), 15f
      )
    )
  }

  override fun onResume() {
    super.onResume()
    presenter.subscribe()
  }

  override fun onMapReady(googleMap: GoogleMap) {
    this.googleMap = googleMap

    presenter.loadCurrentLocation()
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

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main, menu)

    if (menu != null) {
      val item = menu.findItem(R.id.spinner)
      val spinner = item.actionView as Spinner
      spinner.adapter = arrayAdapter
    }

    return super.onCreateOptionsMenu(menu)
  }

  override fun showMaps(maps: List<MomoMapModel>) {
    arrayAdapter.apply {
      clear()
      addAll(maps.map { it.name })
    }
    invalidateOptionsMenu()
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
