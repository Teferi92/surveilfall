package es.santirivera.surveilfall.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.viewholder.DeckViewHolder
import es.santirivera.surveilfall.data.model.Deck
import es.santirivera.surveilfall.data.model.Tournament

class TournamentAdapter(val listener: DeckViewHolder.OnDeckClickedListener) : RecyclerView.Adapter<DeckViewHolder>() {

    private val decks = ArrayList<Deck>()

    override fun getItemCount(): Int {
        return decks.size
    }

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        holder.bind(decks[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val layout = R.layout.item_deck
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return DeckViewHolder(view, listener)
    }

    fun setTournament(tournament: Tournament) {
        decks.clear()
        decks.addAll(tournament.decks)
        notifyDataSetChanged()
    }

}
