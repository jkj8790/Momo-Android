package com.sorrowbeaver.momo.data.entity.mapper

import com.sorrowbeaver.momo.data.entity.UserEntity
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.model.UserType.Normal
import com.sorrowbeaver.momo.domain.model.UserType.Staff
import com.sorrowbeaver.momo.domain.model.UserType.Super
import javax.inject.Inject

class UserEntityDataMapper @Inject constructor() {

  fun transform(userEntity: UserEntity): User {
    val type = when {
      userEntity.is_superuser -> Super
      userEntity.is_staff -> Staff
      else -> Normal
    }

    return User(
      userEntity.pk, type, userEntity.username, userEntity.email,
      userEntity.profile_img, userEntity.date_joined, userEntity.last_login,
      userEntity.is_facebook, userEntity.is_active,
      listOf(), listOf(), listOf()
    )
  }

  fun transform(userEntities: List<UserEntity>?): List<User>? {
    return userEntities?.map { transform(it) }
  }
}
