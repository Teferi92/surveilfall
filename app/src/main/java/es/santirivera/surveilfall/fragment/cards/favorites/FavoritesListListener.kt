package es.santirivera.surveilfall.fragment.cards.favorites

import es.santirivera.surveilfall.base.interfaces.BaseNavigation
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.Set

interface FavoritesListListener : BaseNavigation {

    fun onCardClicked(card: Card)

}
