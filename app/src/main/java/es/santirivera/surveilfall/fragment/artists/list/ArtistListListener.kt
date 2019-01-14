package es.santirivera.surveilfall.fragment.artists.list

import es.santirivera.surveilfall.base.interfaces.BaseNavigation

interface ArtistListListener : BaseNavigation {
    fun onArtistClicked(artist: String)
}
