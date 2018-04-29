package com.sorrowbeaver.momo.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sorrowbeaver.momo.MomoApplication
import com.sorrowbeaver.momo.R
import com.sorrowbeaver.momo.main.MainActivity
import com.sorrowbeaver.momo.model.UserModel
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : Fragment(), LoginContract.View {

  @Inject
  lateinit var presenter: LoginContract.Presenter
  private var progressDialog: AlertDialog? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (activity?.application as MomoApplication).component.inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_login, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    presenter.takeView(this)
    btnLogin.setOnClickListener {
      presenter.login(editId.text.toString(), editPwd.text.toString())
    }
    super.onViewCreated(view, savedInstanceState)
  }

  override fun showLoading() {
    context?.let {
      progressDialog = AlertDialog.Builder(it)
        .setView(R.layout.dialog_progress)
        .show()
    }
  }

  override fun hideLoading() {
    progressDialog?.dismiss()
  }

  override fun onSuccessLogin(user: UserModel) {
    Snackbar.make(btnLogin, "Login done", Snackbar.LENGTH_SHORT).show()
    startActivity(Intent(activity, MainActivity::class.java))
  }

  override fun onLoginError() {
    Snackbar.make(btnLogin, "Login failed", Snackbar.LENGTH_SHORT).show()
  }
}
