package es.santirivera.surveilfall.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.viewholder.CardInDeckViewHolder
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.CardInDeck
import java.util.*

class CardInDeckAdapter(val listener: CardInDeckViewHolder.OnCardClickedListener) : RecyclerView.Adapter<CardInDeckViewHolder>() {

    var cards = ArrayList<CardInDeck>()
    var map = HashMap<String, Card>()

    fun setLists(cardsInDeck: List<CardInDeck>, cardList: List<Card>) {
        cards.clear()
        for (card in cardsInDeck) {
            cards.add(card)
        }
        for (card in cardList) {
            map[card.id] = card
        }
        cards.sortWith(Comparator { o1: CardInDeck, o2: CardInDeck ->
            val card1 = map[o1.scryfallId]!!
            val card2 = map[o2.scryfallId]!!

            val type1 = card1.typeLine
            val type2 = card2.typeLine
            val cmc1 = card1.cmc
            val cmc2 = card2.cmc
            val name1 = card1.name
            val name2 = card2.name
            if (type1.contains("Creature")) {
                if (type2.contains("Creature")) {
                    cmc1 - cmc2
                } else {
                    -1
                }
            } else if (type2.contains("Creature")) {
                1
            } else if (type1.contains("Planeswalker")) {
                if (type2.contains("Planeswalker")) {
                    cmc1 - cmc2
                } else {
                    -1
                }
            } else if (type2.contains("Planeswalker")) {
                1
            } else if (type1.contains("Instant") || type1.contains("Sorcery")) {
                if (type2.contains("Instant") || type2.contains("Sorcery")) {
                    cmc1 - cmc2
                } else {
                    -1
                }
            } else if (type2.contains("Instant") || type2.contains("Sorcery")) {
                1
            } else if (type1.contains("Artifact")) {
                if (type2.contains("Artifact")) {
                    cmc1 - cmc2
                } else {
                    -1
                }
            } else if (type2.contains("Artifact")) {
                1
            } else if (type1.contains("Enchantment")) {
                if (type2.contains("Enchantment")) {
                    cmc1 - cmc2
                } else {
                    -1
                }
            } else if (type2.contains("Enchantment")) {
                1
            } else {
                name1.compareTo(name2)
            }
        })

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardInDeckViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_in_deck, parent, false)
        return CardInDeckViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: CardInDeckViewHolder, position: Int) {
        val cardInDeck = cards[position]
        val card = map[cardInDeck.scryfallId]
        holder.bind(cardInDeck, card!!)
    }

}
