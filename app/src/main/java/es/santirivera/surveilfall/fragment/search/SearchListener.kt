package es.santirivera.surveilfall.fragment.search

import es.santirivera.surveilfall.base.interfaces.BaseNavigation

interface SearchListener : BaseNavigation {

    fun onSearchClicked(query: String, listener: SearchListener)
    fun onRandomClicked(query: String)
    fun onNewQuery(query: String)

}