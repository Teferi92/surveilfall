package es.santirivera.surveilfall.adapter.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.data.model.Card

class CardViewHolder(itemView: View, private val listener: OnCardClickedListener) : RecyclerView.ViewHolder(itemView) {

    interface OnCardClickedListener {
        fun onCardClicked(card: Card)
    }

    private val requestBuilder = Glide.with(itemView)
    private val imageViewIcon: ImageView = itemView.findViewById(R.id.imageViewIcon)

    fun bind(card: Card) {
        itemView.setOnClickListener {
            listener.onCardClicked(card)
        }
        val requestOptions = RequestOptions().placeholder(R.drawable.placeholder)
        requestBuilder.load(card.imageUris?.normal).apply(requestOptions).into(imageViewIcon)
    }

}
