package es.santirivera.surveilfall.fragment.challenges.list

import es.santirivera.surveilfall.base.interfaces.BaseNavigation
import es.santirivera.surveilfall.data.model.Deck

interface ChallengeDecksListListener : BaseNavigation {
    fun onDeckClicked(deck: Deck)
}
