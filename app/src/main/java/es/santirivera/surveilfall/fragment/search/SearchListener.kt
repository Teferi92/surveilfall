package es.santirivera.surveilfall.fragment.search

import es.santirivera.surveilfall.base.interfaces.BaseNavigation

interface SearchListener : BaseNavigation {

    fun onSearchClicked(query: String)
    fun onRandomClicked(query: String)

}