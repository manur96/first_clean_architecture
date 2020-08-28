package com.prueba.firstappclean.view.fragment

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.prueba.firstappclean.R
import com.prueba.firstappclean.extension.hideMe
import com.prueba.firstappclean.extension.showMe
import com.prueba.firstappclean.models.SiteView
import com.prueba.firstappclean.presenter.SitesListPresenter
import com.prueba.firstappclean.view.activity.SiteDetailActivity
import com.prueba.firstappclean.view.adapter.SitesAdapter
import kotlinx.android.synthetic.main.fragment_sites.*
import kotlinx.android.synthetic.main.view_progress.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SitesFragment : RootFragment<SitesListPresenter.View>(), SitesListPresenter.View {

    companion object {
        fun newInstance(): SitesFragment = SitesFragment()
    }

    override val presenter: SitesListPresenter by instance<SitesListPresenter>()

    override val layoutResourceId: Int = R.layout.fragment_sites

    override val fragmentModule: Kodein.Module = Kodein.Module("App") {
        bind<SitesListPresenter>() with provider {
            SitesListPresenter(
                    getSitesUseCase = instance(),
                    view = this@SitesFragment,
                    errorHandler = instance()
            )
        }
    }

    private val adapter = SitesAdapter {
        presenter.onSiteClicked(it)
    }

    override fun initializeUI() {
        sites.adapter = adapter
        sites.layoutManager = LinearLayoutManager(context)
    }

    override fun registerListeners() {
        fabFavorites.setOnClickListener {
            if (presenter.onFavClicked()) {
                fabFavorites.rippleColor = Color.parseColor("#EEEE2E")
                fabFavorites.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#EEEE2E"))
            } else {
                fabFavorites.rippleColor = Color.parseColor("#03DAC5")
                fabFavorites.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#03DAC5"))
            }
        }
    }

    override fun navigateToDetail(id: String) {
        val intent = Intent(activity, SiteDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun showSites(sites: List<SiteView>) {
        adapter.replace(sites.toMutableList())
    }

    override fun showProgress() {
        progressView.showMe()
    }

    override fun hideProgress() {
        progressView.hideMe()
    }

    override fun showErrorDialog() {
        context?.let {
            AlertDialog.Builder(it).setTitle("Error")
                    .setMessage("No se han podido cargar los sitios correctamente")
                    .show()
        }

    }

}