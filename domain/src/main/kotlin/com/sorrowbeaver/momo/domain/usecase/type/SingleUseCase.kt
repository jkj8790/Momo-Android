package com.sorrowbeaver.momo.domain.usecase.type

import io.reactivex.Single

interface SingleUseCase<T, in Params> {

  fun execute(params: Params): Single<T>
}
