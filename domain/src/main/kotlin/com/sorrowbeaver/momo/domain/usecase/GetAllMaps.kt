package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.model.MomoMap.MapSortOption
import com.sorrowbeaver.momo.domain.repository.MapRepository
import com.sorrowbeaver.momo.domain.usecase.GetAllMaps.Params
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
import io.reactivex.Observable

class GetAllMaps(
  private val mapRepository: MapRepository
) : UseCase<List<MomoMap>, Params> {

  data class Params(val sortOption: MapSortOption)

  override fun execute(params: Params): Observable<List<MomoMap>> {
    return mapRepository.getAllMaps(params.sortOption)
  }
}
