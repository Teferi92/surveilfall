package es.santirivera.surveilfall.fragment.challenges.detail

import es.santirivera.surveilfall.base.interfaces.BaseNavigation

interface FormatTournamentListListener : BaseNavigation {

    fun onTournamentClicked(format: String, date: String)

}
