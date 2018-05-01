package com.sorrowbeaver.momo.domain.interactor.type

import io.reactivex.Completable

interface CompletableUseCase<in Params> {

  fun execute(params: Params): Completable
}