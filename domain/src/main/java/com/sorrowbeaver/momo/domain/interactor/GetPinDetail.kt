package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.GetPinDetail.Params
import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.Pin
import com.sorrowbeaver.momo.domain.repository.PinRepository
import io.reactivex.Observable

class GetPinDetail(
  private val pinRepository: PinRepository
) : UseCase<Pin, Params> {

  data class Params(val id: Long)

  override fun execute(params: Params): Observable<Pin> {
    return pinRepository.detail(params.id)
  }
}
