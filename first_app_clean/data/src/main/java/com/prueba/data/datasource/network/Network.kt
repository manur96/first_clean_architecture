package com.prueba.data.datasource.network

import com.prueba.domain.models.Site
import io.reactivex.Single

interface Network {
    fun getAllPoints(): Single<List<Site>>
}
