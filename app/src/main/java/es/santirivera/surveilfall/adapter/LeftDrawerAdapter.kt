package es.santirivera.surveilfall.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.drawer.DrawerItem
import es.santirivera.surveilfall.adapter.viewholder.DrawerViewHolder

class LeftDrawerAdapter(val listener: DrawerViewHolder.OnDrawerItemClickedListener) : RecyclerView.Adapter<DrawerViewHolder>() {

    override fun getItemCount(): Int {
        return DrawerItem.values().size
    }

    override fun onBindViewHolder(holder: DrawerViewHolder, position: Int) {
        holder.bind(DrawerItem.values()[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_drawer, parent, false)
        return DrawerViewHolder(view, listener)
    }
}