package es.santirivera.surveilfall.fragment.search

import android.view.KeyEvent
import android.widget.MultiAutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.WordBankAdapter
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.WordBankItem
import es.santirivera.surveilfall.util.tokenizer.SpaceTokenizer

class SearchView(baseActivity: BaseActivity, presenter: SearchListener) : BaseView<SearchListener>(baseActivity, presenter), TextView.OnEditorActionListener {

    override val contentView: Int get() = R.layout.fragment_search

    private var searchEditText: AppCompatMultiAutoCompleteTextView? = null
    private val wordBankAdapter = WordBankAdapter(baseActivity)

    private val values = arrayOf(
            KeyEvent.KEYCODE_CALL,
            KeyEvent.KEYCODE_DPAD_CENTER,
            KeyEvent.KEYCODE_ENTER,
            KeyEvent.KEYCODE_NUMPAD_ENTER,
            KeyEvent.KEYCODE_SEARCH
    )

    private var newQuery = ""

    override fun prepareView() {
        searchEditText = mainView?.findViewById(R.id.editTextSearch)
        searchEditText!!.setOnEditorActionListener(this)
        searchEditText!!.setAdapter(wordBankAdapter)
        searchEditText!!.setTokenizer(SpaceTokenizer())
    }

    private fun performSearch() {
        presenter.onSearchClicked(searchEditText!!.text.toString(), presenter)
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        for (id in values) {
            if (actionId == id) {
                performSearch()
                return true
            }
        }
        return false
    }

    fun onNewQuery(query: String) {
        newQuery = query
    }

    fun setQueryValue() {
        searchEditText!!.setText(newQuery)
    }

    fun onWordBankReceived(search: List<WordBankItem>) {
        wordBankAdapter.update(search)
    }
}
