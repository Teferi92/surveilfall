package es.santirivera.surveilfall.fragment.challenges.deck

import es.santirivera.surveilfall.base.interfaces.BaseNavigation
import es.santirivera.surveilfall.data.model.Card

interface DeckListDetailListener : BaseNavigation {

    fun onCardClicked(card: Card)

}