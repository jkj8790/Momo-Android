package com.sorrowbeaver.momo.login

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.sorrowbeaver.momo.R
import com.sorrowbeaver.momo.data.repository.UserDataRepository
import com.sorrowbeaver.momo.data.repository.datasource.UserDataStoreFactory
import com.sorrowbeaver.momo.domain.interactor.Login
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginActivity : AppCompatActivity() {

  lateinit var drawerLayout: DrawerLayout
  lateinit var loginPresenter: LoginPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    // Set up the toolbar.
    val toolbar = findViewById(R.id.toolbar) as Toolbar
    setSupportActionBar(toolbar)
    val ab = supportActionBar
    ab?.setHomeAsUpIndicator(android.R.drawable.ic_menu_edit)
    ab?.setDisplayHomeAsUpEnabled(true)

    // Set up the navigation drawer.
    drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
    drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark)
    val navigationView = findViewById(R.id.nav_view) as NavigationView
    setupDrawerContent(navigationView)

    var loginFragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as? LoginFragment
    if (loginFragment == null) {
      // Create the fragment
      loginFragment = LoginFragment()
      val transaction = supportFragmentManager.beginTransaction()
      transaction.add(R.id.contentFrame, loginFragment)
      transaction.commit()
    }

    loginPresenter = LoginPresenter(loginFragment, UserModelDataMapper(), Login(
        UserDataRepository(), Schedulers.io(), AndroidSchedulers.mainThread()
    ))

    // Load previously saved state, if available.
    if (savedInstanceState != null) {
    }

  }

  private fun getUserDataRepository(): UserDataRepository {
    val factory = UserDataStoreFactory()
    return UserDataRepository()
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
