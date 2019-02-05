package es.santirivera.surveilfall.fragment.search

import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.WordBankItem
import es.santirivera.surveilfall.domain.usecases.GetRandomCardUseCase
import es.santirivera.surveilfall.domain.usecases.GetWordBankUseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback
import io.realm.Realm
import io.realm.RealmChangeListener

class SearchFragment : BasePresenter<SearchListener>(), SearchListener {

    override val titleForActivity: String? get() = getString(R.string.search)

    private var view: SearchView? = null

    override fun instanceView(): BaseView<*> {
        view = SearchView(activity as BaseActivity, this)
        return view as SearchView
    }

    override fun loadViewData() {
        val realm = Realm.getDefaultInstance()
        val search = realm
                .where(WordBankItem::class.java)
                .findAllAsync()
        view?.onWordBankReceived(search)
        search.addChangeListener(RealmChangeListener {
            view?.onWordBankReceived(it)
        })

        useCaseHandler!!.execute(
                useCaseProvider!!.getWordBankUseCase,
                object : UseCasePartialCallback<GetWordBankUseCase.OkOutput, GetWordBankUseCase.ErrorOutput>() {
                    override fun isReady(): Boolean {
                        return true
                    }

                    override fun onSuccess(tag: String?, response: GetWordBankUseCase.OkOutput) {
                        realm.beginTransaction()
                        for (word in response.words) {
                            realm.insertOrUpdate(word)
                        }
                        realm.commitTransaction()
                        view?.onWordBankReceived(response.words)
                    }
                }
        )

        // There's no data to load
    }

    override fun onSearchClicked(query: String, listener: SearchListener) {
        (activity as MainActivity).onSearchClicked(query, listener)
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
}
