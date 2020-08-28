package com.prueba.firstappclean.presenter

import com.prueba.domain.interactor.usecases.AddSiteToFavoriteUseCase
import com.prueba.domain.interactor.usecases.GetSiteDetailUseCase
import com.prueba.domain.interactor.usecases.RemoveSiteFromFavoriteUseCase
import com.prueba.domain.models.SiteDetail
import com.prueba.firstappclean.error.ErrorHandler
import com.prueba.firstappclean.mappers.toSiteDetailView
import com.prueba.firstappclean.models.SiteDetailView

class SiteDetailPresenter(
        private val removeSiteFromFavoriteUseCase: RemoveSiteFromFavoriteUseCase,
        private val addSiteToFavoriteUseCase: AddSiteToFavoriteUseCase,
        private val getSiteDetailUseCase: GetSiteDetailUseCase,
        view: View,
        errorHandler: ErrorHandler) : Presenter<SiteDetailPresenter.View>(errorHandler = errorHandler, view = view) {

    val id: String by lazy { view.getId() }

    lateinit var site: SiteDetail

    override fun initialize() {
        view.showProgress()
        getSiteDetailUseCase.execute(
                id = id,
                onSuccess = {
                    site = it
                    view.showDetail(site.toSiteDetailView())
                    view.showRemoveFavButton(site.isFav)
                    view.hideProgress()
                },
                onError = {
                    view.hideProgress()
                    view.showErrorDialog()
                }
        )
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {
        getSiteDetailUseCase.clear()
    }

    fun onFavClicked() {
        addSiteToFavoriteUseCase.execute(
                id = id,
                onComplete = {
                    site.isFav = !site.isFav
                    view.showMessage("Sitio añadido a favorito")
                    view.showRemoveFavButton(site.isFav)
                },
                onError = {
                    view.showError("No se ha podido guardar")
                })
    }

    fun onRemoveClicked() {
        removeSiteFromFavoriteUseCase.execute(
                id = id,
                onComplete = {
                    site.isFav = !site.isFav
                    view.showMessage("Sitio quitado de favoritos")
                    view.showRemoveFavButton(site.isFav)
                },
                onError = {
                    view.showError("No se ha podido eliminar")
                }
        )
    }

    fun onMapClicked() {
        view.navigateToMap()
    }

    interface View : Presenter.View {
        fun showDetail(siteDetail: SiteDetailView)

        fun getId(): String

        fun showRemoveFavButton(isFav: Boolean)

        fun navigateToMap()
    }
}