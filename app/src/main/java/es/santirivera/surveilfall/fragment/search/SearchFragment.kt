package es.santirivera.surveilfall.fragment.search

import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.domain.usecases.implementation.wordbank.UpdateWordBankUseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback
import es.santirivera.surveilfall.domain.usecases.implementation.wordbank.GetWordBankUseCase


class SearchFragment : BasePresenter<SearchListener>(), SearchListener {

    override val titleForActivity: String? get() = getString(R.string.search)

    private lateinit var view: SearchView

    override fun instanceView(): BaseView<*> {
        view = SearchView(activity as BaseActivity, this)
        return view
    }

    override fun loadViewData() {
        useCaseHandler.execute(
                useCaseProvider.getWordBankUseCase,
                object : UseCasePartialCallback<GetWordBankUseCase.OkOutput, GetWordBankUseCase.ErrorOutput>() {
                    override fun onSuccess(tag: String, response: GetWordBankUseCase.OkOutput) {
                        if (response.words.isEmpty()) {
                            loadWords()
                        } else {
                            view.onWordBankReceived(response.words)
                        }
                    }
                }
        )
    }

    private fun loadWords() {
        useCaseHandler.execute(
                useCaseProvider.updateWordBankUseCase,
                object : UseCasePartialCallback<UpdateWordBankUseCase.OkOutput, UpdateWordBankUseCase.ErrorOutput>() {
                    override fun onSuccess(tag: String, response: UpdateWordBankUseCase.OkOutput) {
                        loadViewData()
                    }
                }
        )
    }

    override fun onSearchClicked(query: String, listener: SearchListener) {
        (activity as MainActivity).onSearchClicked(query, listener)
    }

    override fun shouldShowMenu(): Boolean {
        return true
    }

    override fun onNewQuery(query: String) {
        view.onNewQuery(query)
    }

    override fun onResume() {
        super.onResume()
        view.setQueryValue()
    }

}