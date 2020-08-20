package com.prueba.firstappclean.presenter

import com.prueba.domain.interactor.usecases.GetSiteDetailUseCase
import com.prueba.firstappclean.error.ErrorHandler
import com.prueba.firstappclean.mappers.toSiteDetailView
import com.prueba.firstappclean.models.SiteDetailView
import com.prueba.firstappclean.models.SiteView

class SiteDetailPresenter(
        private val getSiteDetailUseCase: GetSiteDetailUseCase,
        view: View,
        errorHandler: ErrorHandler) : Presenter<SiteDetailPresenter.View>(errorHandler = errorHandler, view = view) {

    val id: String by lazy { view.getId() }

    override fun initialize() {
        //view.showProgress() //Error que dice que esta acceciendo a un atributo nulo y tiene que ser not-null
        getSiteDetailUseCase.execute(
                id = id,
                onSuccess = {
                    view.showDetail(it.toSiteDetailView())
                    //view.hideProgress() //Error que dice que esta acceciendo a un atributo nulo y tiene que ser not-null
                },
                onError = {
                    view.hideProgress()
                    //Mostrar dialogo
                    //showAlertDialog()
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

    fun onFavClicked(site: SiteView) {
        view.addToFavorites(site)
    }

/*
    private fun showAlertDialog() {
        AlertDialog.Builder(this).setTitle("Error")
                .setMessage("No se han podido cargar los sitios correctamente")
                .show()
    }
*/

    interface View : Presenter.View {
        fun showDetail(siteDetail: SiteDetailView)

        fun getId(): String

        //Añadir a favorito en preferences
        fun addToFavorites(site: SiteView)
    }
}