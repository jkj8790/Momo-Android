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

  private val fakeId = 101L
  private val fakeName = "name"
  private val fakeEmail = "email"
  private val fakePassword = "password"
  private val fakeProfileUrl = "profile"
  private val fakeJoinedUrl = Date(1000)
  private val fakeLastLoggedIn = Date(1001)

  private lateinit var userEntityDataMapper: UserEntityDataMapper

  @Before
  fun setUp() {
    userEntityDataMapper = UserEntityDataMapper()
  }

  @Test
  fun testTransform() {
    val userEntity = UserEntity(
      fakeId, fakeName, fakePassword,
      fakeEmail, fakeProfileUrl,
      listOf(1L, 2L), fakeJoinedUrl, fakeLastLoggedIn,
      true, true, false, true, listOf()
    )

    val user = User(
      fakeId, UserType.Super, fakeName, fakeEmail,
      fakeProfileUrl, fakeJoinedUrl, fakeLastLoggedIn,
      true, true, listOf(), listOf(), listOf()
    )

    val result = userEntityDataMapper.transform(userEntity)

    assertThat(user, `is`(result))
  }
}
