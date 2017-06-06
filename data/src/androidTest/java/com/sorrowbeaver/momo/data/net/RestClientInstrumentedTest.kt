package com.sorrowbeaver.momo.data.net

import android.support.test.runner.AndroidJUnit4
import com.sorrowbeaver.momo.data.entity.LoginResponse
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.HttpException

@RunWith(AndroidJUnit4::class)
class RestClientInstrumentedTest {

  val FAKE_PK = 0L
  val FAKE_ID = "a"
  val FAKE_PASSWORD = "b"
  val FAKE_LOGIN_RESPONSE = LoginResponse(FAKE_PK, FAKE_ID, FAKE_PASSWORD)
  lateinit var restClient : RestClient

  @Before fun setup() {
    restClient = RestClient()
  }

  @Test fun testLoginFailedWithInvalidAuthentication() {
    val testObserver = TestObserver<LoginResponse>()
    restClient.login(FAKE_ID, FAKE_PASSWORD)
        .subscribe(testObserver)

    testObserver.assertError(HttpException::class.java)
    val response = testObserver.errors()[0] as HttpException
    assertThat(response.code(), `is`(500))
  }
}

