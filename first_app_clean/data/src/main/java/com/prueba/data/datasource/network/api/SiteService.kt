package com.prueba.data.datasource.network.api

import com.prueba.data.model.Data
import com.prueba.data.model.SiteDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SiteService {
    @GET("points")
    fun getAllPoints(): Call<Data>

    @GET("points/{id}")
    fun getPointById(@Path("id") id: String): Call<SiteDetail>
}