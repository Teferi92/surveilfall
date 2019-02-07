package es.santirivera.surveilfall.fragment.momir

import android.view.View
import es.santirivera.surveilfall.base.interfaces.BaseNavigation
import es.santirivera.surveilfall.data.model.Card

interface MomirListener : BaseNavigation {

    fun onCardClicked(card: Card, view: View)
    fun onCardRequested(cmc: Int)
    fun onCardLongClicked(card: Card, view: View)

}