package com.prueba.firstappclean.view.activity

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.prueba.firstappclean.R
import com.prueba.firstappclean.models.SiteView
import com.prueba.firstappclean.presenter.SitesPresenter
import com.prueba.firstappclean.view.adapter.SitesAdapter
import kotlinx.android.synthetic.main.activity_sites.*
import kotlinx.android.synthetic.main.view_progress.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class SitesActivity : RootActivity<SitesPresenter.View>(), SitesPresenter.View {

    override val progress: View by lazy { progressView }

    override val presenter: SitesPresenter by instance<SitesPresenter>()

    override val layoutResourceId: Int = R.layout.activity_sites

    override val activityModule: Kodein.Module = Kodein.Module("App") {
        bind<SitesPresenter>() with provider {
            SitesPresenter(
                    getSitesUseCase = instance(),
                    view = this@SitesActivity,
                    errorHandler = instance()
            )
        }
    }

    private val adapter = SitesAdapter {
        presenter.onSiteClicked(it)
    }

    override fun initializeUI() {
        sites.adapter = adapter
        sites.layoutManager = LinearLayoutManager(this)
    }

    override fun registerListeners() {
        fabFavorites.setOnClickListener {
            presenter.onFavClicked()
        }
    }

    override fun navigateToDetail(id: String) {
        val intent = Intent(this, SiteDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun showSites(sites: List<SiteView>) {
        adapter.addAll(sites.toMutableList())
    }

    override fun showErrorDialog() {
        AlertDialog.Builder(this).setTitle("Error")
                .setMessage("No se han podido cargar los sitios correctamente")
                .show()
    }

}