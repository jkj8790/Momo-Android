package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.Pin
import com.sorrowbeaver.momo.domain.repository.PinRepository
import io.reactivex.Observable

class GetPinsByMapId(
  private val pinRepository: PinRepository
) : UseCase<List<Pin>, GetPinsByMapId.Params> {

  data class Params(val mapId: Long)

  override fun execute(params: Params): Observable<List<Pin>> {
    return pinRepository.getPinsByMapId(params.mapId)
  }
}
