package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.Pin
import com.sorrowbeaver.momo.domain.model.Pin.PinType
import com.sorrowbeaver.momo.domain.repository.PinRepository
import com.sorrowbeaver.momo.domain.usecase.CreatePin.Params
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
import io.reactivex.Observable

class CreatePin(
  private val pinRepository: PinRepository
) : UseCase<Pin, Params> {

  data class Params(
    val name: String,
    val pinType: PinType,
    val authorId: Long,
    val authorName: String,
    val mapId: Long
  )

  override fun execute(params: Params): Observable<Pin> {
    return pinRepository.createPin(
      params.name, params.pinType,
      params.authorId, params.authorName, params.mapId
    )
  }
}
