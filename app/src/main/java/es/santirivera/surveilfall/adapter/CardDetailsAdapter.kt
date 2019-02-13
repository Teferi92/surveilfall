package es.santirivera.surveilfall.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.viewholder.CardDataViewHolder
import es.santirivera.surveilfall.data.model.CardData

class CardDetailsAdapter(private val cardFaces: ArrayList<CardData>, val listener: CardDataViewHolder.OnArtistClickedListener) : RecyclerView.Adapter<CardDataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_data, parent, false)
        return CardDataViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return cardFaces.size
    }

    override fun onBindViewHolder(holder: CardDataViewHolder, position: Int) {
        holder.bind(cardFaces[position])
    }

}
