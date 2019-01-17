package es.santirivera.surveilfall.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.data.model.Legality

class LegalityViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val textViewFormatName: TextView = itemView.findViewById(R.id.textViewFormatName)
    private val textViewFormatLegality: TextView = itemView.findViewById(R.id.textViewFormatLegality)


    fun bind(item: Legality) {
        textViewFormatName.text = item.format
        textViewFormatLegality.text = item.status.description
        when (item.status) {
            Legality.Status.LEGAL -> textViewFormatLegality.setBackgroundResource(R.drawable.legality_legal_background)
            Legality.Status.NOT_LEGAL -> textViewFormatLegality.setBackgroundResource(R.drawable.legality_not_legal_background)
            Legality.Status.BANNED -> textViewFormatLegality.setBackgroundResource(R.drawable.legality_banned_background)
            Legality.Status.RESTRICTED -> textViewFormatLegality.setBackgroundResource(R.drawable.legality_restricted_background)
        }
    }

}