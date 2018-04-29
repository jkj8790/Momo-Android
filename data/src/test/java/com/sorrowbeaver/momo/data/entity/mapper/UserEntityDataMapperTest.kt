package com.sorrowbeaver.momo.data.entity.mapper

import com.sorrowbeaver.momo.data.entity.UserEntity
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.model.UserType
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import java.util.Date

class UserEntityDataMapperTest {

  private val FAKE_ID = 101L
  private val FAKE_NAME = "name"
  private val FAKE_EMAIL = "email"
  private val FAKE_PASSWORD = "password"
  private val FAKE_PROFILE_URL = "profile"
  private val FAKE_JOINED_URL = Date(1000)
  private val FAKE_LAST_LOGGED_IN = Date(1001)

  private lateinit var userEntityDataMapper: UserEntityDataMapper

  @Before
  fun setUp() {
    userEntityDataMapper = UserEntityDataMapper()
  }

  @Test
  fun testTransform() {
    val userEntity = UserEntity(
      FAKE_ID, FAKE_NAME, FAKE_PASSWORD,
      FAKE_EMAIL, FAKE_PROFILE_URL,
      listOf(1L, 2L), FAKE_JOINED_URL, FAKE_LAST_LOGGED_IN,
      true, true, false, true, listOf()
    )

    val user = User(
      FAKE_ID, UserType.Super, FAKE_NAME, FAKE_EMAIL,
      FAKE_PROFILE_URL, FAKE_JOINED_URL, FAKE_LAST_LOGGED_IN,
      true, true, listOf(), listOf(), listOf()
    )

    val result = userEntityDataMapper.transform(userEntity)

    assertThat(user, `is`(result))
  }
}
