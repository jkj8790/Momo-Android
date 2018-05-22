package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.Pin
import com.sorrowbeaver.momo.domain.repository.PinRepository
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
import io.reactivex.Observable

class GetAllPins(
  private val pinRepository: PinRepository
) : UseCase<List<Pin>, Unit> {

  override fun execute(params: Unit): Observable<List<Pin>> {
    return pinRepository.getAllPins()
  }
}
