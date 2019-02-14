package es.santirivera.surveilfall.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.CardInDeck

class CardInDeckViewHolder(view: View, val listener: OnCardClickedListener) : RecyclerView.ViewHolder(view) {

    interface OnCardClickedListener {
        fun onCardClicked(card: Card, view: View)
    }

    private val imageViewIcon: ImageView = itemView.findViewById(R.id.imageViewIcon)
    private val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)

    fun bind(cardInDeck: CardInDeck, card: Card) {
        itemView.setOnClickListener {
            listener.onCardClicked(card, it)
        }
        ViewCompat.setTransitionName(itemView, card.id)
        var imageUris = card.imageUris

        if (imageUris.small == "" && card.cardFaces.size > 0) {
            imageUris = card.cardFaces[0].imageUris!!
        }
        Glide.with(imageViewIcon).load(imageUris.artCrop).into(imageViewIcon)
        textViewTitle.text = textViewTitle.context.getString(R.string.card_in_deck_quantity, cardInDeck.quantity, card.name)
    }

}
