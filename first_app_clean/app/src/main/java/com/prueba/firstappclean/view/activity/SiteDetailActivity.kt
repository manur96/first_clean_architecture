package com.prueba.firstappclean.view.activity

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.view.isVisible
import com.prueba.firstappclean.R
import com.prueba.firstappclean.extension.changeNull
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
                    removeSiteFromFavoriteUseCase = instance(),
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
        removeToFavorites.setOnClickListener {
            presenter.onRemoveClicked()
        }
        goToMap.setOnClickListener {
            presenter.onMapClicked()
        }
    }

    override fun getId(): String = intent.getStringExtra("id")


    override fun showRemoveFavButton(isFav: Boolean) {
        addToFavorites.isVisible = !isFav
        removeToFavorites.isVisible = isFav
    }

    override fun showDetail(siteDetail: SiteDetailView) {
        titleDetail.text = siteDetail.title
        addres.text = siteDetail.address
        addres.changeNull()
        transport.text = siteDetail.transport
        transport.changeNull()
        email.text = siteDetail.email
        email.changeNull()
        geocoordinatesDetail.text = siteDetail.geocoordinates
        description.text = siteDetail.description
        description.changeNull()
        phone.text = siteDetail.phone
        phone.changeNull()
    }

    override fun navigateToMap() {
        val intentUri = Uri.parse("geo:${geocoordinatesDetail.text}")
        val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(packageManager)?.let {
            startActivity(mapIntent)
        }
    }

}