package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.Location
import com.sorrowbeaver.momo.domain.repository.LocationRepository
import io.reactivex.Observable
import javax.inject.Inject

class TrackLocation @Inject constructor(
  private val repository: LocationRepository
) : UseCase<Location, TrackLocation.Params> {

  data class Params(
    val minTime: Long,
    val minDistance: Float
  )

  override fun execute(params: Params): Observable<Location> {
    return repository.trackLocation(params.minTime, params.minDistance)
  }
}
