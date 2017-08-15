package com.sorrowbeaver.momo.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sorrowbeaver.momo.MapsActivity
import com.sorrowbeaver.momo.R
import com.sorrowbeaver.momo.model.UserModel
import kotlinx.android.synthetic.main.fragment_login.btnLogin
import kotlinx.android.synthetic.main.fragment_login.editId
import kotlinx.android.synthetic.main.fragment_login.editPwd

class LoginFragment : Fragment(), LoginContract.View {

  lateinit var presenter: LoginContract.Presenter
  var progressDialog: AlertDialog? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater?.inflate(R.layout.fragment_login, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    btnLogin.setOnClickListener {
      presenter.login(editId.text.toString(), editPwd.text.toString())
    }
  }

  override fun showLoading() {
    progressDialog = AlertDialog.Builder(context)
        .setView(R.layout.dialog_progress)
        .show()
  }

  override fun hideLoading() {
    progressDialog?.dismiss()
  }

  override fun onSuccessLogin(user: UserModel) {
    Snackbar.make(btnLogin, "Login done", Snackbar.LENGTH_SHORT).show()
    startActivity(Intent(activity, MapsActivity::class.java))
  }

  override fun onLoginError() {
    Snackbar.make(btnLogin, "Login failed", Snackbar.LENGTH_SHORT).show()
  }
}
