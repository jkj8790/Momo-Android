package com.sorrowbeaver.momo.data.entity.mapper

import com.sorrowbeaver.momo.data.entity.SearchResultEntity
import com.sorrowbeaver.momo.domain.model.SearchResult

class SearchResultEntityDataMapper {

  val momoMapEntityDataMapper = MomoMapEntityDataMapper()
  val userEntityDataMapper = UserEntityDataMapper()

  fun transform(searchResultEntity: SearchResultEntity) : SearchResult {
    return SearchResult(
        momoMapEntityDataMapper.transform(searchResultEntity.maps),
        userEntityDataMapper.transform(searchResultEntity.users)
    )
  }

}
