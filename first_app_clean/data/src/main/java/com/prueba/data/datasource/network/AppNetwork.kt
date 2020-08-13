package com.prueba.data.datasource.network

import com.prueba.data.mappers.toModel
import com.prueba.domain.models.Site
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


}