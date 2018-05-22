package com.sorrowbeaver.momo.domain.usecase.type

import io.reactivex.Completable

interface CompletableUseCase<in Params> {

  fun execute(params: Params): Completable
}
