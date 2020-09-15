package com.prueba.firstappclean.view.activity

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import com.prueba.firstappclean.R
import com.prueba.firstappclean.presenter.SitesPresenter
import com.prueba.firstappclean.view.adapter.ViewPagerAdapter
import com.prueba.firstappclean.view.fragment.MapsFragment
import com.prueba.firstappclean.view.fragment.SitesFragment
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

    override fun initializeUI() {
        supportActionBar?.elevation = 0F

        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment("Sitios", SitesFragment.newInstance())
        pagerAdapter.addFragment("Mapa", MapsFragment())
        pager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(pager)
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

}