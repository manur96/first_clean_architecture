package com.prueba.data.datasource.network

import com.prueba.domain.models.Site
import com.prueba.domain.models.SiteDetail
import io.reactivex.Single

interface Network {
    fun getAllPoints(): Single<List<Site>>
    fun getPointById(id: String): Single<SiteDetail>
}
