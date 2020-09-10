package com.prueba.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SiteVo(
        @PrimaryKey var id: String = "",
        var title: String = "",
        //var location: LocationVo,
        var hasDetail: Boolean = false
) : RealmObject()