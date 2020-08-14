package com.prueba.domain.interactor.usecases

import com.prueba.domain.executor.Executor
import com.prueba.domain.interactor.SingleInteractor
import com.prueba.domain.models.SiteDetail
import com.prueba.domain.repository.Repository

class GetSiteDetailUseCase(private val repository: Repository, executor: Executor) : SingleInteractor<SiteDetail>(executor = executor) {
    fun execute(id: String, onSuccess: (SiteDetail) -> Unit, onError: (Throwable) -> Unit) {
        super.execute(onSuccess, onError, repository.getSiteById(id))
    }
}