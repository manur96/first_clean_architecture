package com.prueba.data.datasource.network

import com.prueba.data.model.SiteDetailResponseDto
import com.prueba.data.model.SitesResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SiteService {
    @GET("points")
    fun getAllPoints(): Single<SitesResponseDto>

    @GET("points/{id}")
    fun getPointById(@Path("id") id: String): Single<SiteDetailResponseDto>
}