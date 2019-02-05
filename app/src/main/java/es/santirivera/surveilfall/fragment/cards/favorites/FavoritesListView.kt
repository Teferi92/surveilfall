package es.santirivera.surveilfall.fragment.cards.favorites

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.CardAdapter
import es.santirivera.surveilfall.adapter.viewholder.CardViewHolder
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.CardList
import es.santirivera.surveilfall.data.model.Favorite
import io.realm.RealmResults

class FavoritesListView(baseActivity: BaseActivity, presenter: FavoritesListListener) : BaseView<FavoritesListListener>(baseActivity, presenter), CardViewHolder.OnCardClickedListener {

    override val contentView: Int get() = R.layout.fragment_simple_list
    private var recyclerView: RecyclerView? = null
    private val cardAdapter = CardAdapter(this, true)

    override fun prepareView() {
        recyclerView = mainView?.findViewById(R.id.recyclerView)
        val layoutManager = GridLayoutManager(baseActivity, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = cardAdapter
    }

    fun onFavoritesReceived(favorites: RealmResults<Favorite>) {
        cardAdapter.removeAll()
        for (favorite in favorites) {
            cardAdapter.addCard(favorite.card!!)
        }
        cardAdapter.notifyDataSetChanged()
    }

    override fun onCardClicked(card: Card, view: View) {
        presenter.onCardClicked(card, view)
    }


    fun resetAdapter() {
        cardAdapter.removeAll()
        cardAdapter.notifyDataSetChanged()
    }
}
