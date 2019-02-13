package es.santirivera.surveilfall.fragment.challenges.detail

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.SimpleStringAdapter
import es.santirivera.surveilfall.adapter.viewholder.StringViewHolder
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView

class FormatTournamentListView(baseActivity: BaseActivity, listener: FormatTournamentListListener) : BaseView<FormatTournamentListListener>(baseActivity, listener), StringViewHolder.OnStringClickedListener {

    override val contentView: Int get() = R.layout.fragment_simple_list
    private var tournamentAdapter = SimpleStringAdapter(ArrayList(), this, false)
    private lateinit var recyclerView: RecyclerView
    lateinit var format: String

    override fun prepareView() {
        recyclerView = mainView.findViewById(R.id.recyclerView)
        val layoutManager = GridLayoutManager(baseActivity, 1)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = tournamentAdapter
    }

    override fun onStringClicked(item: String) {
        presenter.onTournamentClicked(format, item)
    }


    fun onTournamentsLoaded(tournaments: List<String>) {
        tournamentAdapter.updateItems(tournaments)
    }

}
