package com.prueba.domain.interactor.usecases

import com.prueba.domain.executor.Executor
import com.prueba.domain.interactor.SingleInteractor
import com.prueba.domain.models.Site

class GetSitesUseCase(executor: Executor) : SingleInteractor<List<Site>>(executor = executor) {

    fun execute(onSuccess: (List<Site>) -> Unit, onError: (Throwable) -> Unit) {
        //Call to repository
    }
}