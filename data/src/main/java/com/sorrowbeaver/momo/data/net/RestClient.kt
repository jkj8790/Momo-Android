package com.sorrowbeaver.momo.data.net

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestClient {
  val restApi = createMomoService()

  fun login(id : String, password : String) = restApi.login(id, password)

  fun signup(email : String, username : String, password : String)
      = restApi.signup(email, username, password)

  fun createMomoService() : MomoApi {
    val builder = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://momo.kizmo04.com")

    return builder.build().create(MomoApi::class.java)
  }
}
