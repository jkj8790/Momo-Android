package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.GetUsers.Params
import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.User
import com.sorrowbeaver.momo.domain.model.UserSortOption
import com.sorrowbeaver.momo.domain.repository.UserRepository
import io.reactivex.Observable

class GetUsers(
  private val userRepository: UserRepository
) : UseCase<List<User>, Params> {

  data class Params(val sortOption: UserSortOption)

  override fun execute(params: Params): Observable<List<User>> {
    return userRepository.users(params.sortOption)
  }
}
