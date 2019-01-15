package es.santirivera.surveilfall.fragment.cards.detail

import es.santirivera.surveilfall.base.interfaces.BaseNavigation

interface CardDetailListener : BaseNavigation {

    fun onDownloadRequested()
    fun onResolutionSelected()
    fun onRulingsRequested()
    fun onShareRequested()
    fun onAddToFavoritesRequested()

}
