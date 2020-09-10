package com.prueba.data.datasource

import com.prueba.domain.models.Site
import com.prueba.domain.models.SiteDetail
import io.reactivex.Maybe
import io.reactivex.Single

interface Database {
    fun saveSites(site: List<Site>)
    fun saveDatail(siteDetail: SiteDetail)
    fun getSites(): Single<List<Site>>
    fun getDetail(id: String): Maybe<SiteDetail>
    fun getSitesWithDetail(): List<String>
}