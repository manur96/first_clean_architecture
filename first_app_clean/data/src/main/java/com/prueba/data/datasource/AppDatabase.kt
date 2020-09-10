package com.prueba.data.datasource

import com.prueba.data.extensions.RxRealm
import com.prueba.data.extensions.save
import com.prueba.data.mappers.toModel
import com.prueba.data.mappers.toVo
import com.prueba.data.model.SiteDetailVo
import com.prueba.data.model.SiteVo
import com.prueba.domain.models.Site
import com.prueba.domain.models.SiteDetail
import io.reactivex.Maybe
import io.reactivex.Single
import io.realm.Realm

class AppDatabase : Database {
    override fun saveSites(sites: List<Site>) {
        val sitesVos = sites.map { it.toVo() }
        sitesVos.save()
    }

    override fun saveDatail(siteDetail: SiteDetail) = siteDetail.toVo().save()

    override fun getSites(): Single<List<Site>> =
            RxRealm.getList {
                it.where(SiteVo::class.java)
                        .findAll()
            }.map { it.map { it.toModel() } }.toSingle()

    override fun getDetail(id: String): Maybe<SiteDetail> =
            RxRealm.getElement {
                it.where(SiteDetailVo::class.java)
                        .equalTo(SiteDetailVo.PRIMARY_KEY, id)
                        .findFirst()
            }.map { it.toModel() }

    override fun getSitesWithDetail(): List<String> {
        return Realm.getDefaultInstance().where(SiteDetailVo::class.java).findAll().map{
            it.id
        }
    }

            /*RxRealm.getList { it.where(SiteDetailVo::class.java).findAll() }.map {
                it.map {
                    it.id
                }
            }*/


}