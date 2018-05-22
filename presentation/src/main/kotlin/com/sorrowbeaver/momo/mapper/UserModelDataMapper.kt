package com.sorrowbeaver.momo.mapper

import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.model.UserModel

open class UserModelDataMapper {
  open fun transform(user: User) = UserModel(
    user.id,
    user.userName,
    user.profileUrl
  )
}
