package com.prueba.domain.interactor.usecases

import com.prueba.domain.executor.Executor
import com.prueba.domain.interactor.SingleInteractor
import com.prueba.domain.models.Site
import com.prueba.domain.repository.Repository

class GetSitesUseCase(private val repository: Repository, executor: Executor) : SingleInteractor<List<Site>>(executor = executor) {
    fun execute(filter: Boolean, onSuccess: (List<Site>) -> Unit, onError: (Throwable) -> Unit) {
        super.execute(onSuccess, onError, repository.getAllSites(filter))
    }
}