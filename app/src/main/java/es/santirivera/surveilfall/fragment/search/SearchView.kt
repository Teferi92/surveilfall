package es.santirivera.surveilfall.fragment.search

import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView
import android.widget.TextView
import android.widget.Toast

class SearchView(baseActivity: BaseActivity, presenter: SearchListener) : BaseView<SearchListener>(baseActivity, presenter), TextView.OnEditorActionListener {


    private var searchEditText: EditText? = null

    private val values = arrayOf(
            KeyEvent.KEYCODE_CALL,
            KeyEvent.KEYCODE_DPAD_CENTER,
            KeyEvent.KEYCODE_ENTER,
            KeyEvent.KEYCODE_NUMPAD_ENTER,
            KeyEvent.KEYCODE_SEARCH
    )

    override fun getContentView(): Int {
        return R.layout.fragment_search
    }

    override fun prepareView() {
        searchEditText = mainView.findViewById(R.id.editTextSearch)
        searchEditText!!.setOnEditorActionListener(this)
    }

    private fun performSearch() {
        presenter.onSearchClicked(searchEditText!!.text.toString())
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
}
