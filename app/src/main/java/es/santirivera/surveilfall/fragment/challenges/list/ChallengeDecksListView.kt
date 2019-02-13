package es.santirivera.surveilfall.fragment.challenges.list

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.TournamentAdapter
import es.santirivera.surveilfall.adapter.viewholder.DeckViewHolder
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Deck
import es.santirivera.surveilfall.data.model.Tournament

class ChallengeDecksListView(baseActivity: BaseActivity, listener: ChallengeDecksListListener) : BaseView<ChallengeDecksListListener>(baseActivity, listener), DeckViewHolder.OnDeckClickedListener {

    override val contentView: Int
        get() = R.layout.fragment_simple_list

    private val adapter = TournamentAdapter(this)
    private lateinit var recyclerView: RecyclerView


    override fun prepareView() {
        recyclerView = mainView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(mainView.context)
        recyclerView.adapter = adapter
    }

    fun onTournamentLoaded(tournament: Tournament) {
        adapter.setTournament(tournament)
    }

    override fun onDeckClicked(item: Deck) {
        presenter.onDeckClicked(item)
    }
}
