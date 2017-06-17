package com.sorrowbeaver.momo.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sorrowbeaver.momo.R
import com.sorrowbeaver.momo.login.LoginContract.Presenter

class LoginFragment : Fragment(), LoginContract.View {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val root = inflater?.inflate(R.layout.fragment_login, container, false)



    return root
  }

  override fun setPresenter(presenter: Presenter) {
  }
}
