package com.hex.revoluttestapplication.net

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrenciesApi {

    @GET("api/android/latest")
    fun getCurrenciesAsync(
        @Query("base") base: String = "EUR"
    ) : Deferred<Response<CurrenciesDto>>
}
