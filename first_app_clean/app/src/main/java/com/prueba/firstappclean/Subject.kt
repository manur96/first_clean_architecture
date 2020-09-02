package com.prueba.firstappclean

import com.prueba.domain.models.Site
import io.reactivex.subjects.PublishSubject

object Subject {
    val listSitesSubject: PublishSubject<List<Site>> = PublishSubject.create()
}