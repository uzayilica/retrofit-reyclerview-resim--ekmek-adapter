package com.example.retrofitresimcekme

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/photos")
    fun getData():Call<List<Images>>
}