package com.prueba.data.datasource

import com.prueba.domain.models.Site
import io.reactivex.Single

interface Database {
    fun saveSites(events: List<Site>)
    fun getSites(): Single<List<Site>>
}