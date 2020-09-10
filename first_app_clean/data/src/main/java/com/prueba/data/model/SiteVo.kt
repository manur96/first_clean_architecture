package com.prueba.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SiteVo(
        @PrimaryKey var id: String = "",
        var title: String = "",
        var lat: Double = 0.0,
        var lng: Double = 0.0,
        var hasDetail: Boolean = false
) : RealmObject()