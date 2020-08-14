package com.prueba.data.repository

import com.prueba.data.datasource.network.Network
import com.prueba.domain.models.Site
import com.prueba.domain.models.SiteDetail
import com.prueba.domain.repository.Repository
import io.reactivex.Single

class AppRepository(private val network: Network) : Repository {
    override fun getAllSites(): Single<List<Site>> = network.getAllPoints()
    override fun getSiteById(id: String): Single<SiteDetail> = network.getPointById(id)
}