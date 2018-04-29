package com.sorrowbeaver.momo.data.net

import android.support.test.runner.AndroidJUnit4
import com.sorrowbeaver.momo.data.entity.LoginResponse
import com.sorrowbeaver.momo.data.entity.UserEntity
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.HttpException

@RunWith(AndroidJUnit4::class)
class RestClientInstrumentedTest {

  private val fakeId = "a"
  private val fakePassword = "b"
  private lateinit var restClient: RestClient

  @Before
  fun setup() {
    restClient = RestClient()
  }

  @Test
  fun testLoginFailedWithInvalidAuthentication() {
    val testObserver = TestObserver<LoginResponse>()
    restClient.login(fakeId, fakePassword)
      .subscribe(testObserver)

    testObserver.assertError(HttpException::class.java)
    val response = testObserver.errors()[0] as HttpException
    assertThat(response.code(), `is`(500))
  }

  @Test
  fun testSignupFailedWithInvalidAuthentication() {
    val testObserver = TestObserver<UserEntity>()
    restClient.signup("0", "1", "2")
      .subscribe(testObserver)

    testObserver.assertError(HttpException::class.java)
    val response = testObserver.errors()[0] as HttpException
    assertThat(response.code(), `is`(500))
  }
}
