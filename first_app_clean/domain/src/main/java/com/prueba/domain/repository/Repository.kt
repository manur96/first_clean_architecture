package com.prueba.domain.repository

import com.prueba.domain.models.Site
import com.prueba.domain.models.SiteDetail
import io.reactivex.Single


/**
 * Repository.
 */

interface Repository {
    fun getAllSites(): Single<List<Site>>
    fun getSiteById(id: String): Single<SiteDetail>
}
