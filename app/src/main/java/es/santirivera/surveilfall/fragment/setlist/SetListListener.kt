package es.santirivera.surveilfall.fragment.setlist

import es.santirivera.surveilfall.base.interfaces.BaseNavigation
import es.santirivera.surveilfall.data.model.Set

interface SetListListener : BaseNavigation {

    fun onSetClicked(set: Set)

}
