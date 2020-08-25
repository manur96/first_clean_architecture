package com.prueba.data.repository

import com.prueba.data.datasource.network.Network
import com.prueba.data.persistence.Settings
import com.prueba.domain.models.Site
import com.prueba.domain.models.SiteDetail
import com.prueba.domain.repository.Repository
import io.reactivex.Completable
import io.reactivex.Single

class AppRepository(private val network: Network, private val settings: Settings) : Repository {
    override fun getAllSites(filter: Boolean): Single<List<Site>> {
        var sites = network.getAllPoints()
        if (filter) {
            sites = sites.map { sites ->
                val favoriteSites = settings.getFavorites()
                sites.filter { site -> favoriteSites.contains(site.id) }
            }
        }
        return sites
    }


    override fun getSiteById(id: String): Single<SiteDetail> = network.getPointById(id)
    override fun addSiteToFavorites(id: String): Completable = settings.addToFavorite(idSite = id) //SharedPreference = lista
}