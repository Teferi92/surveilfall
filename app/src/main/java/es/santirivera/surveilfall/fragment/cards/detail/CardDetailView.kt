package es.santirivera.surveilfall.fragment.cards.detail

import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card

class CardDetailView(baseActivity: BaseActivity, presenter: CardDetailListener) : BaseView<CardDetailListener>(baseActivity, presenter) {

    override fun getContentView(): Int {
        return R.layout.fragment_simple_list
    }

    override fun prepareView() {

    }

    fun onCardLoaded(card: Card) {

    }
}
