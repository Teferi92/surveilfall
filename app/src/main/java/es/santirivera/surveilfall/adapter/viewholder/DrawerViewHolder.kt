package es.santirivera.surveilfall.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.drawer.DrawerItem

class DrawerViewHolder(itemView: View, private val listener: OnDrawerItemClickedListener) : RecyclerView.ViewHolder(itemView) {

    interface OnDrawerItemClickedListener {
        fun onDrawerItemClicked(item: DrawerItem)
    }

    private val textViewSetName: TextView = itemView.findViewById(R.id.textViewTitle)

    fun bind(item: DrawerItem) {
        textViewSetName.text = textViewSetName.resources.getString(item.nameId)
        itemView.setOnClickListener {
            listener.onDrawerItemClicked(item)
        }
    }

}