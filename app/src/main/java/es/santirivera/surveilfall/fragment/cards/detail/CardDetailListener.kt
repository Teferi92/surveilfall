package es.santirivera.surveilfall.fragment.cards.detail

import es.santirivera.surveilfall.base.interfaces.BaseNavigation

interface CardDetailListener : BaseNavigation {

    fun isFavorite()
    fun onArtistClicked(artist: String)
    fun toggleFavoriteAction(favorite: Boolean)

}
