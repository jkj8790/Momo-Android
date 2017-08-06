package com.sorrowbeaver.momo.data.repository.datasource.search

class SearchDataStoreFactory {

  fun create() : SearchDataStore {
    return FakeSearchDataStore()
  }

}