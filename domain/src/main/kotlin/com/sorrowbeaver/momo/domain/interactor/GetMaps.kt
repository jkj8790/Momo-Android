package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.GetMaps.Params
import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.model.MomoMap.MapSortOption
import com.sorrowbeaver.momo.domain.repository.MapRepository
import io.reactivex.Observable

class GetMaps(
  private val mapRepository: MapRepository
) : UseCase<List<MomoMap>, Params> {

  data class Params(val sortOption: MapSortOption)

  override fun execute(params: Params): Observable<List<MomoMap>> {
    return mapRepository.maps(params.sortOption)
  }
}
