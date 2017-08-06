package com.sorrowbeaver.momo.data.repository.datasource.pin

class PinDataStoreFactory {

  fun create() : PinDataStore {
    return FakePinDataStore()
  }

}