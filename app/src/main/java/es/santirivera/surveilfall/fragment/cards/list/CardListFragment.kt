package es.santirivera.surveilfall.fragment.cards

import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.domain.usecases.GetCardsForQueryUseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback
import es.santirivera.surveilfall.fragment.setlist.CardListListener
import es.santirivera.surveilfall.fragment.setlist.CardListView

class CardListFragment : BasePresenter<CardListListener>(), CardListListener {

    private var view: CardListView? = null
    private val cardCallback: CardListCallback = CardListCallback()
    var query: String? = ""
    var fragmentTitle: String? = ""

    private var page = 1
    private var lastAskedPage = 1

    override fun instanceView(): BaseView<*> {
        view = CardListView(activity as BaseActivity, this)
        return view as CardListView
    }

    override fun loadViewData() {
        val input = GetCardsForQueryUseCase.Input(this.query!!, page, GetCardsForQueryUseCase.PrintsToInclude.PRINTS)
        val useCase = useCaseProvider.getCardsForQueryUseCase
        useCaseHandler.execute(useCase, input, cardCallback)
    }

    override fun onCardClicked(card: Card) {
        (activity as MainActivity).onCardClicked(card)
    }

    override fun getTitleForActivity(): String {
        return fragmentTitle!!
    }

    override fun onBottomReached(currentPage: Int) {
        if (page > lastAskedPage) {
            loadViewData()
            lastAskedPage++
        }
    }

    private inner class CardListCallback : UseCasePartialCallback<GetCardsForQueryUseCase.OkOutput, GetCardsForQueryUseCase.ErrorOutput>() {
        override fun isReady(): Boolean {
            return true
        }

        override fun onSuccess(tag: String?, response: GetCardsForQueryUseCase.OkOutput) {
            view!!.onCardListReceived(response.cardList, page)
            page++
        }

    }
}