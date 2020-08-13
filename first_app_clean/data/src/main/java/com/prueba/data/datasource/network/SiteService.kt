package com.prueba.data.datasource.network

import com.prueba.data.model.SitesResponseDto
import io.reactivex.Single
import retrofit2.http.GET

interface SiteService {
    @GET("points")
    fun getAllPoints(): Single<SitesResponseDto>
/*
    @GET("points/{id}")
    fun getPointById(@Path("id") id: String): Call<SiteDetail>*/
}