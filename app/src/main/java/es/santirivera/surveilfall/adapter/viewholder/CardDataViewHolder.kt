package es.santirivera.surveilfall.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.data.model.CardData
import es.santirivera.surveilfall.util.spanner.Spanner

class CardDataViewHolder(itemView: View, val listener: OnArtistClickedListener) : RecyclerView.ViewHolder(itemView) {

    interface OnArtistClickedListener {
        fun onArtistClicked(artist: String)
    }

    private var textViewCardText: TextView = itemView.findViewById(R.id.textViewCardText)
    private var textViewCardType: TextView = itemView.findViewById(R.id.textViewCardType)
    private var textViewCardName: TextView = itemView.findViewById(R.id.textViewCardName)
    private var textViewCardFlavor: TextView = itemView.findViewById(R.id.textViewCardFlavor)
    private var textViewCardArtist: TextView = itemView.findViewById(R.id.textViewArtist)
    private var textViewCardCost: TextView = itemView.findViewById(R.id.textViewCardCost)
    private var textViewCardStats: TextView = itemView.findViewById(R.id.textViewCardStats)

    fun bind(card: CardData) {
        textViewCardName.text = card.name
        textViewCardType.text = card.typeLine
        textViewCardText.text = Spanner.addManaCostsToText(itemView.context, card.oracleText, textViewCardText.textSize.toInt())

        if (card.power != null && !card.power!!.isEmpty()) {
            // Creature
            textViewCardStats.visibility = View.VISIBLE
            textViewCardStats.text = itemView.resources.getString(R.string.power_toughness, card.power, card.toughness)
        } else if (card.loyalty != null && !card.loyalty!!.isEmpty()) {
            // Planeswalker
            textViewCardStats.visibility = View.VISIBLE
            textViewCardStats.text = itemView.resources.getString(R.string.loyalty, card.loyalty)
        } else {
            // Other
            textViewCardStats.visibility = View.GONE
        }

        if (card.manaCost != null && (!card.manaCost!!.isEmpty())) {
            textViewCardCost.visibility = View.VISIBLE
            textViewCardCost.text = Spanner.addManaCostsToText(itemView.context, card.manaCost!!, textViewCardCost.textSize.toInt())
        } else {
            textViewCardCost.visibility = View.GONE
        }
        if (card.flavorText != null && (!card.flavorText!!.isEmpty())) {
            textViewCardFlavor.visibility = View.VISIBLE
            textViewCardFlavor.text = card.flavorText
        } else {
            textViewCardFlavor.visibility = View.GONE
        }
        textViewCardArtist.text = itemView.resources.getString(R.string.illustration, card.artist)
        textViewCardArtist.setOnClickListener { listener.onArtistClicked(card.artist!!) }
    }

}
