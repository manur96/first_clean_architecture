package com.prueba.domain.repository

import com.prueba.domain.models.Site
import com.prueba.domain.models.SiteDetail
import io.reactivex.Completable
import io.reactivex.Single


/**
 * Repository.
 */

interface Repository {
    fun getAllSites(onlyFavourites: Boolean): Single<List<Site>>
    fun getSiteById(id: String): Single<SiteDetail>
    fun addSiteToFavorites(id: String): Completable
    fun removeSiteFromFavorites(id: String): Completable
}
