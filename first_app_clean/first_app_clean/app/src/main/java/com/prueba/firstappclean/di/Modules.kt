package com.prueba.firstappclean.di

import android.content.Context
import org.kodein.di.Kodein
import com.prueba.domain.executor.Executor
import com.prueba.firstappclean.error.AndroidErrorHandler
import com.prueba.firstappclean.error.ErrorHandler
import com.prueba.firstappclean.executor.RxExecutor
import org.kodein.di.generic.bind
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
    // Add here data dependencies
}

val dataModule = Kodein.Module("dataModule") {
    // Add here data dependencies
}
