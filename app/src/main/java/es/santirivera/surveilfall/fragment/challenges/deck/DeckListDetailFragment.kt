package es.santirivera.surveilfall.fragment.challenges.deck

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.Deck
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback
import es.santirivera.surveilfall.domain.usecases.implementation.cards.GetCardCollectionUseCase

class DeckListDetailFragment : BasePresenter<DeckListDetailListener>(), DeckListDetailListener {

    lateinit var deck: Deck
    lateinit var title: String
    lateinit var view: DeckListDetailView
    private lateinit var menuReload: MenuItem

    override val titleForActivity: String
        get() = title

    override fun instanceView(): BaseView<*> {
        setHasOptionsMenu(true)
        view = DeckListDetailView(activity as BaseActivity, this)
        return view
    }

    override fun loadViewData() {
        useCaseHandler.execute(
                useCaseProvider.getCardCollectionUseCase,
                GetCardCollectionUseCase.Input(deck.getCardIdentifiers()),
                object : UseCasePartialCallback<GetCardCollectionUseCase.OkOutput, GetCardCollectionUseCase.ErrorOutput>() {
                    override fun onSuccess(tag: String, response: GetCardCollectionUseCase.OkOutput) {
                        view.onDataReceived(deck, response.cardList)
                    }
                }
        )
    }

    override fun shouldShowMenu(): Boolean {
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_reload, menu)
        menuReload = menu.findItem(R.id.action_reload)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item == menuReload) {
            loadViewData()
            true
        } else {
            false
        }
    }

    override fun onCardClicked(card: Card) {
        (activity as MainActivity).onCardClicked(card, null)
    }
}
