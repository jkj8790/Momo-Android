package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.Location
import com.sorrowbeaver.momo.domain.repository.LocationRepository
import com.sorrowbeaver.momo.domain.usecase.type.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

open class GetCurrentLocation @Inject constructor(
  private val repository: LocationRepository
) : SingleUseCase<Location, Unit> {

  override fun execute(params: Unit): Single<Location> {
    return repository.getCurrentLocation()
  }
}
