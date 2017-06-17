package com.sorrowbeaver.momo.login

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityInstrumentedTest {

  @Rule @JvmField
  val activityRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

  @Test
  fun testHello() {
    onView(withText("HELLO"))
        .check(matches(isDisplayed()))
  }


}
