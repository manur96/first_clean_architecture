package com.prueba.firstappclean.di

import android.content.Context
import com.prueba.data.datasource.network.AppNetwork
import com.prueba.data.datasource.network.Network
import com.prueba.data.datasource.network.SiteService
import com.prueba.data.datasource.network.createService
import com.prueba.data.persistence.AppSettings
import com.prueba.data.persistence.Settings
import com.prueba.data.repository.AppRepository
import com.prueba.domain.executor.Executor
import com.prueba.domain.interactor.usecases.AddSiteToFavoriteUseCase
import com.prueba.domain.interactor.usecases.GetSiteDetailUseCase
import com.prueba.domain.interactor.usecases.GetSitesUseCase
import com.prueba.domain.repository.Repository
import com.prueba.firstappclean.BuildConfig
import com.prueba.firstappclean.error.AndroidErrorHandler
import com.prueba.firstappclean.error.ErrorHandler
import com.prueba.firstappclean.executor.RxExecutor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * Modules
 */
fun appModule(context: Context) = Kodein.Module("appModule") {
    bind<Context>() with singleton { context }
    bind<Executor>() with singleton { RxExecutor() }
    bind<ErrorHandler>() with singleton { AndroidErrorHandler(context = context) }
}

val domainModule = Kodein.Module("domainModule") {
    bind() from singleton { GetSitesUseCase(executor = instance(), repository = instance()) }
    bind() from singleton { GetSiteDetailUseCase(executor = instance(), repository = instance())}
    bind() from singleton { AddSiteToFavoriteUseCase(repository = instance(), executor = instance()) }
}

val dataModule = Kodein.Module("dataModule") {
    bind<Repository>() with singleton { AppRepository(network = instance(), settings = instance()) }
    bind<Network>() with singleton { AppNetwork(siteService = instance()) }
    bind<Settings>() with singleton { AppSettings(context = instance(), name = "first_app_clean") }
    bind<SiteService>() with singleton { createService<SiteService>(endPoint = "http://t21services.herokuapp.com/") }
}
