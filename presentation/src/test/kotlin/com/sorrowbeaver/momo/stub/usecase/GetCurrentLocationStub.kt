package com.sorrowbeaver.momo.stub.usecase

import com.sorrowbeaver.momo.domain.interactor.GetCurrentLocation
import com.sorrowbeaver.momo.domain.model.Location
import com.sorrowbeaver.momo.dummy.DummyLocationRepository
import io.reactivex.Single

object GetCurrentLocationStub : GetCurrentLocation(DummyLocationRepository) {

  private val seoul = Location(37.532600, 127.024612)

  override fun execute(params: Unit): Single<Location> {
    return Single.just(seoul)
  }
}