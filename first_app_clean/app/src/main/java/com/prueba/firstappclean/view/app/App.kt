package com.prueba.firstappclean.view.app

import android.app.Application
import com.prueba.firstappclean.di.appModule
import com.prueba.firstappclean.di.dataModule
import com.prueba.firstappclean.di.domainModule
import io.realm.Realm
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

/**
 * App.
 */
class App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule(this@App))
        import(domainModule)
        import(dataModule)
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }

}
