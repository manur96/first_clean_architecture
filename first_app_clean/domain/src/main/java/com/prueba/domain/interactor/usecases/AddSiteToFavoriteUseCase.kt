package com.prueba.domain.interactor.usecases

import com.prueba.domain.executor.Executor
import com.prueba.domain.interactor.CompletableInteractor
import com.prueba.domain.repository.Repository

class AddSiteToFavoriteUseCase(private val repository: Repository, executor: Executor) : CompletableInteractor(executor = executor) {
    fun execute(id: String, onComplete: () -> Unit, onError: (Throwable) -> Unit) {
        super.execute(onComplete, onError, repository.addSiteToFavorites(id))
    }
}