package com.sorrowbeaver.momo.domain.interactor.type

import io.reactivex.Single

interface SingleUseCase<T, in Params> {

  fun execute(params: Params): Single<T>
}
