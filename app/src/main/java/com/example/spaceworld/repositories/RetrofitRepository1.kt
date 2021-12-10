package com.example.spaceworld.repositories


class RetrofitRepository1 {
    var retrofitClient : RetrofitService = RetrofitClient().retrofitService
    suspend fun getData() = retrofitClient.getMarsData()
}