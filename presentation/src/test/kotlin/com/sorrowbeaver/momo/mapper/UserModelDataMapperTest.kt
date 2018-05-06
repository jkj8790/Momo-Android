package com.sorrowbeaver.momo.mapper

import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.model.UserType
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import java.util.Date

class UserModelDataMapperTest {

  private val fakeName = "name"
  private val fakeProfileUrl = "url"

  @Test
  fun testTransform() {
    val user = User(
      0L, UserType.Super, fakeName,
      "email", fakeProfileUrl, Date(), Date(), true, true,
      emptyList(), emptyList(), emptyList()
    )
    val mapper = UserModelDataMapper()

    val userModel = mapper.transform(user)

    assertThat(userModel.userName, `is`(fakeName))
    assertThat(userModel.profileUrl, `is`(fakeProfileUrl))
  }
}
