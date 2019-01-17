package es.santirivera.surveilfall.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.viewholder.LegalityViewHolder
import es.santirivera.surveilfall.data.model.Legality

class LegalityAdapter(private val legalities: ArrayList<Legality>) : RecyclerView.Adapter<LegalityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LegalityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_legality, parent, false)
        return LegalityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return legalities.size
    }

    override fun onBindViewHolder(holder: LegalityViewHolder, position: Int) {
        holder.bind(legalities[position])
    }

}
