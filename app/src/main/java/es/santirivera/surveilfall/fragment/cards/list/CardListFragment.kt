package es.santirivera.surveilfall.fragment.cards.list

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.domain.usecases.implementation.cards.GetCardsForQueryUseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback
import es.santirivera.surveilfall.fragment.search.SearchListener
import es.santirivera.surveilfall.util.enumwrap.SortMethodDescriptor
import es.santirivera.surveilfall.util.enumwrap.SortOrderDescriptor


class CardListFragment : BasePresenter<CardListListener>(), CardListListener {

    override val titleForActivity: String? get() = if (isQueryEditable) query!! else fragmentTitle!!

    private val cardCallback: CardListCallback = CardListCallback()

    private lateinit var view: CardListView
    private lateinit var menuItemSearch: MenuItem
    private lateinit var menuItemSort: MenuItem

    var query: String? = ""
    var fragmentTitle: String? = ""
    var prints: GetCardsForQueryUseCase.PrintsToInclude = GetCardsForQueryUseCase.PrintsToInclude.PRINTS
    var sortMethod: GetCardsForQueryUseCase.SortMethod = GetCardsForQueryUseCase.SortMethod.NAME
    var sortOrder: GetCardsForQueryUseCase.SortOrder = GetCardsForQueryUseCase.SortOrder.AUTO
    var isQueryEditable: Boolean = false
    var searchListener: SearchListener? = null

    var justOpened = false

    private var page = 1
    private var lastAskedPage = 1

    override fun onPause() {
        super.onPause()
        searchListener?.onNewQuery(query!!)
    }

    override fun instanceView(): BaseView<*> {
        setHasOptionsMenu(true)
        view = CardListView(activity as BaseActivity, this)
        return view
    }

    override fun loadViewData() {
        val input = GetCardsForQueryUseCase.Input(this.query!!, page, prints, sortMethod, sortOrder)
        val useCase = useCaseProvider.getCardsForQueryUseCase
        useCaseHandler.execute(useCase, input, cardCallback)
    }

    override fun onCardClicked(card: Card, view: View) {
        (activity as MainActivity).onCardClicked(card, view)
    }


    fun performNewQuery(newQuery: String) {
        query = newQuery
        page = 1
        view.resetAdapter()
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
        if (isQueryEditable) {
            inflater.inflate(R.menu.menu_search, menu)
            menuItemSearch = menu.findItem(R.id.action_search)
            val searchView = menuItemSearch.actionView as SearchView
            searchView.setQuery(query, false)
            searchView.clearFocus()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(newQuery: String): Boolean {
                    performNewQuery(newQuery)
                    if (!searchView.isIconified) {
                        searchView.isIconified = true
                    }
                    menuItemSearch.collapseActionView()
                    return true
                }

                override fun onQueryTextChange(s: String): Boolean {
                    if (s == "" && justOpened) {
                        justOpened = false
                        searchView.setQuery(query, false)
                    }
                    return false
                }

            })
        }
        inflater.inflate(R.menu.menu_order, menu)
        menuItemSort = menu.findItem(R.id.action_sort)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item) {
            menuItemSearch -> {
                justOpened = true
                val searchView = menuItemSearch.actionView as SearchView
                searchView.setQuery(query, false)
                searchView.clearFocus()
                true
            }
            menuItemSort -> {
                val sortMethods = SortMethodDescriptor.getDescriptorsList(activity!!)
                val sortOrders = SortOrderDescriptor.getDescriptorsList(activity!!)
                AlertDialog.Builder(activity!!).setTitle(R.string.title_sort_method).setItems(sortMethods) { dialog, which ->
                    val sortMethod: GetCardsForQueryUseCase.SortMethod = SortMethodDescriptor.forDescription(activity!!, sortMethods[which]).method
                    dialog.dismiss()
                    AlertDialog.Builder(activity!!).setTitle(R.string.title_sort_order).setItems(sortOrders) { dialog2, which2 ->
                        val sortOrder: GetCardsForQueryUseCase.SortOrder = SortOrderDescriptor.forDescription(activity!!, sortOrders[which2]).sortOrder
                        this.sortMethod = sortMethod
                        this.sortOrder = sortOrder
                        dialog2.dismiss()
                        performNewQuery(query!!)
                    }.show()
                }.show()
                true
            }
            else -> false
        }

    }


    private inner class CardListCallback : UseCasePartialCallback<GetCardsForQueryUseCase.OkOutput, GetCardsForQueryUseCase.ErrorOutput>() {

        override fun onSuccess(tag: String?, response: GetCardsForQueryUseCase.OkOutput) {
            view.onCardListReceived(response.cardList, page)
            page++
        }

    }
}