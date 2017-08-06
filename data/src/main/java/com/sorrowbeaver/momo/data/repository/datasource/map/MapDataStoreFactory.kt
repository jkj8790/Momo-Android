package com.sorrowbeaver.momo.data.repository.datasource.map

import com.sorrowbeaver.momo.data.repository.datasource.user.FakeUserDataStore

class MapDataStoreFactory {

  fun create() : MapDataStore {
    return FakeMapDataStore(FakeUserDataStore())
  }

}
