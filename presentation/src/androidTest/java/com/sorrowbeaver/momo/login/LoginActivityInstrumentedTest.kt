package com.sorrowbeaver.momo.login

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityInstrumentedTest {

  @Rule @JvmField
  val activityRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

}
