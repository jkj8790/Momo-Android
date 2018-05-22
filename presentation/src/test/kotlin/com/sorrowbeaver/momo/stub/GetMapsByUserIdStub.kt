package com.sorrowbeaver.momo.stub

import com.sorrowbeaver.momo.domain.interactor.GetMapsByUserId
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.dummy.DummyMapRepository
import io.reactivex.Observable

class SuccessGetMapsByUserId(
  private val expected: MomoMap
) : GetMapsByUserId(DummyMapRepository) {

  override fun execute(params: Params): Observable<List<MomoMap>> {
    return Observable.just(listOf(expected))
  }
}