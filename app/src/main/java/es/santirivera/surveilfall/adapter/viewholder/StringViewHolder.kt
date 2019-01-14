package es.santirivera.surveilfall.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R

class StringViewHolder(itemView: View, private val listener: OnStringClickedListener) : RecyclerView.ViewHolder(itemView) {

    interface OnStringClickedListener {
        fun onStringClicked(item: String)
    }

    private val textViewSetName: TextView = itemView.findViewById(R.id.textViewTitle)

    fun bind(item: String) {
        textViewSetName.text = item
        itemView.setOnClickListener {
            listener.onStringClicked(item)
        }
    }

}