package es.santirivera.surveilfall.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.viewholder.CardViewHolder
import es.santirivera.surveilfall.data.model.Card

class CardAdapter(val listener: CardViewHolder.OnCardClickedListener, val filterRepeats: Boolean) : RecyclerView.Adapter<CardViewHolder>() {

    private val cards: ArrayList<Card> = ArrayList()

    fun addCard(card: Card) {
        if (!filterRepeats || (filterRepeats && !cards.contains(card))) {
            cards.add(card)
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(cards[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = View.inflate(parent.context, R.layout.item_card, null)
        return CardViewHolder(view, listener)
    }

    fun removeAll() {
        cards.clear()
    }

}
