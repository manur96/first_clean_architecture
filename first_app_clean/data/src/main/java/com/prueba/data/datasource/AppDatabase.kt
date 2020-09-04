package com.prueba.data.datasource

import com.prueba.data.extensions.RxRealm
import com.prueba.data.extensions.save
import com.prueba.data.mappers.toModel
import com.prueba.data.mappers.toVo
import com.prueba.data.model.SiteVo
import com.prueba.domain.models.Site
import io.reactivex.Single

class AppDatabase : Database {
    override fun saveSites(events: List<Site>) {
        val sitesVos = events.map { it.toVo() }
        sitesVos.save()
    }

    override fun getSites(): Single<List<Site>> =
            RxRealm.getList {
                it.where(SiteVo::class.java)
                        .findAll()
            }.map { it.map { it.toModel() } }
                    .toSingle()

}