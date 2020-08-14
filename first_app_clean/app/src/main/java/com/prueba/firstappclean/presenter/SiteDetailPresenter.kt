package com.prueba.firstappclean.presenter

import com.prueba.domain.interactor.usecases.GetSiteDetailUseCase
import com.prueba.firstappclean.error.ErrorHandler
import com.prueba.firstappclean.mappers.toSiteDetailView
import com.prueba.firstappclean.models.SiteDetailView

class SiteDetailPresenter(
        private val getSiteDetailUseCase: GetSiteDetailUseCase,
        view: View,
        errorHandler: ErrorHandler) : Presenter<SiteDetailPresenter.View>(errorHandler = errorHandler, view = view) {

    override fun initialize() {

    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }

    fun onDetailClick(id: String) {
        getSiteDetailUseCase.execute(
                id = id,
                onSuccess = {
                    view.showDetail(it.toSiteDetailView())
                },
                onError = {

                }
        )
    }

    interface View : Presenter.View {
        fun showDetail(siteDetail: SiteDetailView)
    }
}