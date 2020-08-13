package com.prueba.domain.repository

import com.prueba.domain.models.Site
import io.reactivex.Single


/**
 * Repository.
 */

interface Repository {
    fun getAllSites(): Single<List<Site>>
}
