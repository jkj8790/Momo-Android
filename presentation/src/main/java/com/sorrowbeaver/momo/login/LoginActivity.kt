package com.sorrowbeaver.momo.login

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import com.sorrowbeaver.momo.R
import com.sorrowbeaver.momo.data.repository.datasource.user.UserDataRepository
import com.sorrowbeaver.momo.data.repository.datasource.user.UserDataStoreFactory
import com.sorrowbeaver.momo.domain.interactor.Login
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.drawerLayout
import kotlinx.android.synthetic.main.activity_login.navigationView
import kotlinx.android.synthetic.main.activity_login.toolbar

class LoginActivity : AppCompatActivity() {

  lateinit var loginPresenter: LoginPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    // Set up the toolbar.
    setSupportActionBar(toolbar)
    val ab = supportActionBar
    ab?.setHomeAsUpIndicator(android.R.drawable.ic_menu_edit)
    ab?.setDisplayHomeAsUpEnabled(true)

    // Set up the navigation drawer.
    drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark)
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

    loginFragment.presenter = loginPresenter

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
