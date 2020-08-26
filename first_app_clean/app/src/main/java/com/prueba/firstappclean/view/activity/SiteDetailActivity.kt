package com.prueba.firstappclean.view.activity

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.prueba.firstappclean.R
import com.prueba.firstappclean.models.SiteDetailView
import com.prueba.firstappclean.presenter.SiteDetailPresenter
import kotlinx.android.synthetic.main.activity_site_detail.*
import kotlinx.android.synthetic.main.view_progress.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SiteDetailActivity : RootActivity<SiteDetailPresenter.View>(), SiteDetailPresenter.View {

    override val progress: View by lazy { progressView }

    override val presenter: SiteDetailPresenter by instance<SiteDetailPresenter>()

    override val layoutResourceId: Int = R.layout.activity_site_detail

    override val activityModule: Kodein.Module = Kodein.Module("App") {
        bind<SiteDetailPresenter>() with provider {
            SiteDetailPresenter(
                    addSiteToFavoriteUseCase = instance(),
                    getSiteDetailUseCase = instance(),
                    view = this@SiteDetailActivity,
                    errorHandler = instance()
            )
        }
    }

    override fun initializeUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun registerListeners() {
        addToFavorites.setOnClickListener {
            presenter.onFavClicked()
        }
    }

    override fun getId(): String {
        return intent.getStringExtra("id")
    }

    override fun hideFavButton(isFav: Boolean) {
        if (isFav)
            addToFavorites.isVisible = false
    }

    override fun showDetail(siteDetail: SiteDetailView) {
        titleDetail.text = siteDetail.title
        addres.text = siteDetail.address
        transport.text = siteDetail.transport
        email.text = siteDetail.email
        geocoordinatesDetail.text = siteDetail.geocoordinates
        description.text = siteDetail.description
        phone.text = siteDetail.phone
    }


    override fun showErrorDialog() {
        AlertDialog.Builder(this).setTitle("Error")
                .setMessage("No se han podido cargar los sitios correctamente")
                .show()
    }

}