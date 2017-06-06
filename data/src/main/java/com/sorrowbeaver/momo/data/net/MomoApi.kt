package com.sorrowbeaver.momo.data.net

import com.sorrowbeaver.momo.data.entity.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MomoApi {

  @FormUrlEncoded
  @POST("/api/member/login/")
  fun login(
      @Field("id") id : String,
      @Field("password") password : String
  ) : Observable<LoginResponse>

}
