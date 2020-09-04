package com.prueba.data.repository

import com.prueba.data.datasource.Database
import com.prueba.data.datasource.network.Network
import com.prueba.data.persistence.Settings
import com.prueba.domain.models.Site
import com.prueba.domain.models.SiteDetail
import com.prueba.domain.repository.Repository
import io.reactivex.Completable
import io.reactivex.Single

class AppRepository(private val network: Network, private val settings: Settings, private val database: Database) : Repository {

    override fun getAllSites(onlyFavourites: Boolean): Single<List<Site>> {
        val networkResponse = network.getAllPoints()

        val favoriteSites = if (settings.hasFavorites()) {
            settings.getFavorites()
        } else {
            listOf()
        }

        return if (onlyFavourites) {
            networkResponse.onErrorResumeNext {
                database.getSites()
            }.map { list ->
                list
                        .filter { site -> site.id in favoriteSites }
                        .map {
                            it.fav = true
                            it
                        }
            }
        } else {
            networkResponse.onErrorResumeNext {
                database.getSites()
            }.map { list ->
                list
                        .map { site ->
                            site.fav = site.id in favoriteSites
                            site
                        }
            }
        }
    }

    override fun getSiteById(id: String): Single<SiteDetail> = network.getPointById(id).map { site ->
        site.isFav = id in settings.getFavorites()
        return@map site
    }

    override fun addSiteToFavorites(id: String): Completable = settings.addToFavorite(idSite = id)

    override fun removeSiteFromFavorites(id: String): Completable = settings.removeFromFavorite(idSite = id)

}

