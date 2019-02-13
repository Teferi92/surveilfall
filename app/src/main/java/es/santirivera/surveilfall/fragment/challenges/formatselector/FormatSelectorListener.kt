package es.santirivera.surveilfall.fragment.challenges.formatselector

import es.santirivera.surveilfall.base.interfaces.BaseNavigation

interface FormatSelectorListener : BaseNavigation {

    fun onFormatSelected(format: String)

}