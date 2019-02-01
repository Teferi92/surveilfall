package es.santirivera.surveilfall.fragment.cards.detail

import es.santirivera.surveilfall.base.interfaces.BaseNavigation
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.ImageUris

interface CardDetailListener : BaseNavigation {

    fun isFavorite()
    fun onArtistClicked(artist: String)
    fun toggleFavoriteAction(favorite: Boolean)
    fun onSaveCardArtRequested(uris: ImageUris, cardName: String)
    fun onShowReprintsClicked(card: Card?)

}
