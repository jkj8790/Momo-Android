package com.sorrowbeaver.momo.stub.mapper

import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.mapper.UserModelDataMapper
import com.sorrowbeaver.momo.model.UserModel

class UserModelMapperStub(
  private val expected: UserModel
) : UserModelDataMapper() {

  override fun transform(user: User): UserModel {
    return expected
  }
}