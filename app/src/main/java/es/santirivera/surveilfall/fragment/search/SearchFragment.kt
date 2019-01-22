package es.santirivera.surveilfall.fragment.search

import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.domain.usecases.GetRandomCardUseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback

class SearchFragment : BasePresenter<SearchListener>(), SearchListener {

    private var view: SearchView? = null

    override fun instanceView(): BaseView<*> {
        view = SearchView(activity as BaseActivity, this)
        return view as SearchView
    }

    override fun loadViewData() {
        // There's no data to load
    }

    override fun getTitleForActivity(): String {
        return getString(R.string.search)
    }

    override fun onSearchClicked(query: String, listener: SearchListener) {
        (activity as MainActivity).onSearchClicked(query, listener)
    }

    override fun onRandomClicked(query: String) {
        val useCase = useCaseProvider.getRandomCardUseCase
        val input = GetRandomCardUseCase.Input(query)
        useCaseHandler.execute(useCase, input, RandomCaseCallback())
    }

    override fun shouldShowMenu(): Boolean {
        return true
    }

    override fun onNewQuery(query: String) {
        view!!.onNewQuery(query)
    }

    override fun onResume() {
        super.onResume()
        view!!.setQueryValue()
    }

    inner class RandomCaseCallback : UseCasePartialCallback<GetRandomCardUseCase.OkOutput, GetRandomCardUseCase.ErrorOutput>() {
        override fun isReady(): Boolean {
            return true
        }

        override fun onSuccess(tag: String?, response: GetRandomCardUseCase.OkOutput?) {
            (activity as MainActivity).onCardClicked(response!!.card)
        }

    }
}
