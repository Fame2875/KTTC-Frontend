package com.pskmax.kkct_app.APIManager


import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://quotable.io/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface KKCTBackendApiServiceApiService {
    @GET("/helloworld")
    fun getHello() : String
    @GET("/quotes")
    suspend fun getQuotes() : Response<QuoteList>
}


object KKCTApi {
    val retrofitService: KKCTBackendApiServiceApiService by lazy { retrofit.create(KKCTBackendApiServiceApiService::class.java) }
}


