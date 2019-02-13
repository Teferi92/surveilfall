package es.santirivera.surveilfall.adapter.viewholder

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.data.model.Deck

class DeckViewHolder(itemView: View, val listener: OnDeckClickedListener) : RecyclerView.ViewHolder(itemView) {

    interface OnDeckClickedListener {
        fun onDeckClicked(item: Deck)
    }

    private val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
    private val textViewPlayer: TextView = itemView.findViewById(R.id.textViewPlayer)
    private val textViewResult: TextView = itemView.findViewById(R.id.textViewResult)


    @SuppressLint("SetTextI18n")
    fun bind(item: Deck) {
        textViewTitle.text = item.n
        textViewPlayer.text = item.pl
        textViewResult.text = "${item.w} - ${item.l}"
        itemView.setOnClickListener {
            listener.onDeckClicked(item)
        }
    }
}
