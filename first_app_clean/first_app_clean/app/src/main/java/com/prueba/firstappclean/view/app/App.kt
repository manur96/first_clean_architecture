package com.prueba.firstappclean.view.app

import android.app.Application
import com.prueba.firstappclean.di.appModule
import com.prueba.firstappclean.di.dataModule
import com.prueba.firstappclean.di.domainModule
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

}
