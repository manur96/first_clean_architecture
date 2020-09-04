package com.prueba.firstappclean.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.prueba.domain.models.Site
import com.prueba.firstappclean.R
import com.prueba.firstappclean.extension.hideMe
import com.prueba.firstappclean.extension.showMe
import com.prueba.firstappclean.presenter.MapsPresenter
import kotlinx.android.synthetic.main.view_progress.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider


class MapsFragment : RootFragment<MapsPresenter.View>(), MapsPresenter.View {

    companion object {
        private val BARCELONA = LatLng(41.38879, 2.15899)
    }

    private var googleMap: GoogleMap? = null

    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(BARCELONA))
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10F), 3000, null)
        val uiSettings = googleMap.uiSettings
        uiSettings.isScrollGesturesEnabled = true
        uiSettings.isZoomControlsEnabled = true
        presenter.onMapReady()
    }

    override val presenter: MapsPresenter by instance<MapsPresenter>()

    override val layoutResourceId: Int = R.layout.fragment_maps

    override val fragmentModule: Kodein.Module = Kodein.Module("App") {
        bind<MapsPresenter>() with provider {
            MapsPresenter(
                    view = this@MapsFragment,
                    errorHandler = instance()
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun initializeUI() {
        //nothing to do
    }

    override fun registerListeners() {
        //nothing to do
    }

    override fun showSites(sites: List<Site>) {
        googleMap?.clear()
        sites.map {
            googleMap?.addMarker(MarkerOptions().position(LatLng(it.location.lat, it.location.lng)).title(it.title))
        }
    }

    override fun showProgress() {
        progressView.showMe()
    }

    override fun hideProgress() {
        progressView.hideMe()
    }
}