package es.santirivera.surveilfall.fragment.cards.list

import es.santirivera.surveilfall.base.interfaces.BaseNavigation
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.Set

interface CardListListener : BaseNavigation {

    fun onCardClicked(card: Card)

    fun onBottomReached(currentPage: Int)

}
