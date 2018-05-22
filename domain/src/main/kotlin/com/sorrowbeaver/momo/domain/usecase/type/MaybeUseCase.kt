package com.sorrowbeaver.momo.domain.usecase.type

import io.reactivex.Maybe

interface MaybeUseCase<T, in Params> {

  fun execute(params: Params): Maybe<T>
}
