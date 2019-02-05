package es.santirivera.surveilfall.fragment.search

import es.santirivera.surveilfall.base.interfaces.BaseNavigation

interface SearchListener : BaseNavigation {

    fun onSearchClicked(query: String, listener: SearchListener)
    fun onNewQuery(query: String)

}