package es.santirivera.surveilfall.fragment.cards.list

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.domain.usecases.GetCardsForQueryUseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback

import androidx.appcompat.widget.SearchView
import es.santirivera.surveilfall.fragment.search.SearchListener


class CardListFragment : BasePresenter<CardListListener>(), CardListListener {

    private var view: CardListView? = null
    private val cardCallback: CardListCallback = CardListCallback()
    private var search: MenuItem? = null

    var query: String? = ""
    var fragmentTitle: String? = ""
    var prints: GetCardsForQueryUseCase.PrintsToInclude = GetCardsForQueryUseCase.PrintsToInclude.PRINTS
    var isQueryEditable: Boolean = false
    var searchListener: SearchListener? = null

    private var page = 1
    private var lastAskedPage = 1

    override fun instanceView(): BaseView<*> {
        setHasOptionsMenu(isQueryEditable)
        view = CardListView(activity as BaseActivity, this)
        return view as CardListView
    }

    override fun loadViewData() {
        val input = GetCardsForQueryUseCase.Input(this.query!!, page, prints)
        val useCase = useCaseProvider.getCardsForQueryUseCase
        useCaseHandler.execute(useCase, input, cardCallback)
    }

    override fun onCardClicked(card: Card) {
        (activity as MainActivity).onCardClicked(card)
    }

    override fun getTitleForActivity(): String {
        return if (isQueryEditable) query!! else fragmentTitle!!
    }

    fun performNewQuery(newQuery: String) {
        query = newQuery
        page = 1
        view!!.resetAdapter()
        updateTitle()
        loadViewData()
        if (searchListener != null) {
            searchListener!!.onNewQuery(newQuery)
        }
    }

    override fun onBottomReached(currentPage: Int) {
        if (page > lastAskedPage) {
            loadViewData()
            lastAskedPage++
        }
    }

    override fun shouldShowMenu(): Boolean {
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_search, menu)
        search = menu.findItem(R.id.action_search)
        val searchView = search!!.actionView as SearchView
        searchView.setQuery(query, false)
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newQuery: String): Boolean {
                performNewQuery(newQuery)
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                search!!.collapseActionView()
                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                return s.isEmpty()
            }
        })
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