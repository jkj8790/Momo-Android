package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.type.SingleUseCase
import com.sorrowbeaver.momo.domain.model.Location
import com.sorrowbeaver.momo.domain.repository.LocationRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCurrentLocation @Inject constructor(
  private val repository: LocationRepository
) : SingleUseCase<Location, Unit> {

  override fun execute(params: Unit): Single<Location> {
    return repository.getCurrentLocation()
  }
}
