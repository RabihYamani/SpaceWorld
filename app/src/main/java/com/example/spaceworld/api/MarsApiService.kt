package com.example.spaceworld.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService{
    @GET("rovers")
    fun getRovers():
            Call<String>

    object MarsApi{
        val retrofitService: MarsApiService by lazy {
            retrofit.create(MarsApiService::class.java)
        }
    }

}

