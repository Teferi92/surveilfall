package es.santirivera.surveilfall.fragment.artists.list

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.SimpleStringAdapter
import es.santirivera.surveilfall.adapter.viewholder.StringViewHolder
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView


class ArtistListView(baseActivity: BaseActivity, presenter: ArtistListListener) : BaseView<ArtistListListener>(baseActivity, presenter), StringViewHolder.OnStringClickedListener {

    private var recyclerView: RecyclerView? = null

    override fun getContentView(): Int {
        return R.layout.fragment_simple_list
    }

    override fun prepareView() {
        recyclerView = mainView.findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(baseActivity)
    }

    override fun onStringClicked(item: String) {
        presenter.onArtistClicked(item)
    }

    fun onArtistsReceived(artists: List<String>) {
        val adapter = SimpleStringAdapter(artists, this, false)
        recyclerView!!.adapter = adapter
    }

}
