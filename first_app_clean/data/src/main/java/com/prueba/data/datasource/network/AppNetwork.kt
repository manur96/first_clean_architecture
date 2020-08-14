package com.prueba.data.datasource.network

import com.prueba.data.mappers.toModel
import com.prueba.domain.models.Site
import com.prueba.domain.models.SiteDetail
import io.reactivex.Single

class AppNetwork(private val siteService: SiteService) : Network {
    override fun getAllPoints(): Single<List<Site>> =
            siteService.getAllPoints()
                    .map { response -> response.sites }
                    .map {
                        it.map {
                            it.toModel()
                        }
                    }

    override fun getPointById(id: String): Single<SiteDetail> =
            siteService.getPointById(id)
                    .map {
                        it.toModel()
                    }
}