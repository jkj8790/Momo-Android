package com.sorrowbeaver.momo.domain.interactor.type

import io.reactivex.Observable

interface UseCase<T, in Params> {

  fun execute(params: Params): Observable<T>
}
