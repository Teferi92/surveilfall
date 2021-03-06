package es.santirivera.surveilfall.fragment.cards.list

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

class CardListView(baseActivity: BaseActivity, presenter: CardListListener) : BaseView<CardListListener>(baseActivity, presenter), CardViewHolder.OnCardClickedListener {


    override val contentView: Int get() = R.layout.fragment_simple_list
    private lateinit var recyclerView: RecyclerView
    private val cardAdapter = CardAdapter(this, true)

    private var currentPage: Int = 0


    override fun prepareView() {
        recyclerView = mainView.findViewById(R.id.recyclerView)
        val layoutManager = GridLayoutManager(baseActivity, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = cardAdapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {
                    presenter.onBottomReached(currentPage)
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    fun onCardListReceived(cards: CardList, currentPage: Int) {
        this.currentPage = currentPage
        for (card in cards.data!!) {
            cardAdapter.addCard(card)
        }
        cardAdapter.notifyDataSetChanged()
    }

    override fun onCardClicked(card: Card, view: View) {
        presenter.onCardClicked(card, view)
    }

    override fun onCardLongClicked(card: Card, view: View) {
        // Do nothing
    }

    fun resetAdapter() {
        cardAdapter.removeAll()
        cardAdapter.notifyDataSetChanged()
    }
}
