package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.model.UserSortOption
import com.sorrowbeaver.momo.domain.repository.UserRepository
import com.sorrowbeaver.momo.domain.usecase.GetUsers.Params
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
import io.reactivex.Observable

class GetUsers(
  private val userRepository: UserRepository
) : UseCase<List<User>, Params> {

  data class Params(val sortOption: UserSortOption)

  override fun execute(params: Params): Observable<List<User>> {
    return userRepository.users(params.sortOption)
  }
}
