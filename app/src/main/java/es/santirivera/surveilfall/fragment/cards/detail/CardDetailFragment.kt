package es.santirivera.surveilfall.fragment.cards.detail

import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.domain.usecases.GetCardInSetUseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback

class CardDetailFragment : BasePresenter<CardDetailListener>(), CardDetailListener {


    private var view: CardDetailView? = null

    var card: Card? = null

    override fun instanceView(): BaseView<*> {
        view = CardDetailView(activity as BaseActivity, this)
        return view as CardDetailView
    }


    override fun loadViewData() {
        activity!!.title = card!!.name
        view?.onCardLoaded(card!!)
    }


    override fun getTitleForActivity(): String {
        return card!!.name
    }

    override fun onDownloadRequested() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResolutionSelected() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRulingsRequested() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onShareRequested() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAddToFavoritesRequested() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onArtistClicked(artist: String) {
        (activity as MainActivity).onArtistClicked(artist)
    }

    override fun shouldShowMenu(): Boolean {
        return false
    }
}