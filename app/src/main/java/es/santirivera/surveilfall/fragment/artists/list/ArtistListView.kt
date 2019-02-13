package es.santirivera.surveilfall.fragment.artists.list

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.SimpleStringAdapter
import es.santirivera.surveilfall.adapter.viewholder.StringViewHolder
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView


class ArtistListView(baseActivity: BaseActivity, presenter: ArtistListListener) : BaseView<ArtistListListener>(baseActivity, presenter), StringViewHolder.OnStringClickedListener {

    override val contentView: Int get() = R.layout.fragment_simple_list

    private lateinit var recyclerView: RecyclerView


    override fun prepareView() {
        recyclerView = mainView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(baseActivity, 2)
    }

    override fun onStringClicked(item: String) {
        presenter.onArtistClicked(item)
    }

    fun onArtistsReceived(artists: List<String>) {
        val adapter = SimpleStringAdapter(artists, this, false)
        recyclerView.adapter = adapter
    }

}
