package com.prueba.firstappclean.view.adapter

import android.graphics.Color
import android.view.View
import com.prueba.firstappclean.R
import com.prueba.firstappclean.models.SiteView
import kotlinx.android.synthetic.main.item_site.view.*

class SitesAdapter(onSiteClick: (SiteView) -> Unit) : RootAdapter<SiteView>(onItemClickListener = onSiteClick) {

    override val itemLayoutId: Int = R.layout.item_site

    override fun viewHolder(view: View): RootViewHolder<SiteView> = ViewHolder(view)

    class ViewHolder(view: View) : RootViewHolder<SiteView>(itemView = view) {
        override fun bind(model: SiteView) {
            itemView.title.text = model.title //"${if (model.fav) "FAV - " else ""} ${model.title}"
            if (model.fav)
                itemView.title.setTextColor(Color.parseColor("#0060CD"))
            else
                itemView.title.setTextColor(Color.DKGRAY)
            itemView.geocoordinates.text = model.geocoordinates
        }
    }

}