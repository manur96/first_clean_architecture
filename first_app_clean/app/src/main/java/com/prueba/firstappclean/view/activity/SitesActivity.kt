package com.prueba.firstappclean.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.prueba.data.datasource.network.api.SiteService
import com.prueba.data.model.Data
import com.prueba.firstappclean.R
import com.prueba.firstappclean.view.adapter.SitesAdapter
import kotlinx.android.synthetic.main.activity_sites.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SitesActivity : AppCompatActivity() {

    private val adapter = SitesAdapter(
            onDetailClick = {
                val intent = Intent(this, SiteDetailActivity::class.java)
                intent.putExtra("id", it.id)
                startActivity(intent)
            }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sites)

        sites.adapter = adapter
        sites.layoutManager = LinearLayoutManager(this)

        generateSites()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://t21services.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun generateSites() {
        val service = getRetrofit().create(SiteService::class.java)
        service.getAllPoints().enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>?, response: Response<Data>?) {
                val data = response?.body()
                if (data != null) {
                    for (site in data.list) {
                        adapter.add(site)
                    }
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}