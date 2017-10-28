package com.sorrowbeaver.momo.mapper

import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.model.UserModel

class UserModelDataMapper {

  fun transform(user: User) = UserModel(user.userName, user.profileUrl)

}
