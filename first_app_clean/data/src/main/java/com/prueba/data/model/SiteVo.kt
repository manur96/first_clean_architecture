package com.prueba.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SiteVo(
        @PrimaryKey var id: String = "",
        var title: String = "",
        var fav: Boolean = false
) : RealmObject()