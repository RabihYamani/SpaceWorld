package com.example.spaceworld.api

import com.example.spaceworld.models.RoverModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService{
    @GET("rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY")
    fun getRoversCuriosity():
            Call<String>

    @GET("rovers/spirit/photos?sol=1000&api_key=DEMO_KEY")
    fun getRoversSpirit():
            Call<String>

    @GET("rovers/opportunity/photos?sol=1000&api_key=DEMO_KEY")
    fun getRoversOpportunity():
            Call<String>

    object MarsApi{
        val retrofitService: MarsApiService by lazy {
            retrofit.create(MarsApiService::class.java)
        }
    }

}


