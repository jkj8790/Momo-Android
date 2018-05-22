package com.sorrowbeaver.momo.stub.usecase

import com.sorrowbeaver.momo.domain.interactor.GetMapsByUserId
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.dummy.DummyMapRepository
import io.reactivex.Observable
import java.util.Date

object GetMapsByUserIdStub : GetMapsByUserId(DummyMapRepository) {

  private val fake = MomoMap(
    0L, "fake map", "I am fake map",
    false, 1L, emptyList(), Date()
  )

  override fun execute(params: Params): Observable<List<MomoMap>> {
    return Observable.just(listOf(fake))
  }
}