package com.sorrowbeaver.momo.data.entity.mapper

import com.sorrowbeaver.momo.data.entity.SearchResultEntity
import com.sorrowbeaver.momo.domain.model.SearchResult
import javax.inject.Inject

class SearchResultEntityDataMapper @Inject constructor(
  private val momoMapEntityDataMapper: MomoMapEntityDataMapper,
  private val userEntityDataMapper: UserEntityDataMapper
) {

  fun transform(searchResultEntity: SearchResultEntity): SearchResult {
    return SearchResult(
      momoMapEntityDataMapper.transform(searchResultEntity.maps),
      userEntityDataMapper.transform(searchResultEntity.users)
    )
  }
}
