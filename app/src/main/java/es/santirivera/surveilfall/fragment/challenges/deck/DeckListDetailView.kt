package es.santirivera.surveilfall.fragment.challenges.deck

import android.view.View
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.CardInDeckAdapter
import es.santirivera.surveilfall.adapter.viewholder.CardInDeckViewHolder
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.Deck

class DeckListDetailView(baseActivity: BaseActivity, presenter: DeckListDetailListener) : BaseView<DeckListDetailListener>(baseActivity, presenter), CardInDeckViewHolder.OnCardClickedListener {

    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var scrollView: NestedScrollView
    private lateinit var layout: View

    private val adapterMaindeck = CardInDeckAdapter(this)
    private val adapterSideboard = CardInDeckAdapter(this)

    override val contentView: Int
        get() = R.layout.fragment_decklist

    override fun prepareView() {
        recyclerView1 = mainView.findViewById(R.id.recyclerView1)
        recyclerView2 = mainView.findViewById(R.id.recyclerView2)
        recyclerView1.layoutManager = LinearLayoutManager(baseActivity)
        recyclerView2.layoutManager = LinearLayoutManager(baseActivity)
        recyclerView1.adapter = adapterMaindeck
        recyclerView2.adapter = adapterSideboard
        layout = mainView.findViewById(R.id.layout_recyclers)
        scrollView = mainView.findViewById(R.id.scrollView)
        scrollView.isNestedScrollingEnabled = false
    }

    fun onDataReceived(deck: Deck, list: List<Card>) {
        adapterMaindeck.setLists(deck.maindeck, list)
        adapterSideboard.setLists(deck.sideboard, list)
        layout.visibility = View.VISIBLE
    }

    override fun onCardClicked(card: Card, view: View) {
        presenter.onCardClicked(card)
    }

}
