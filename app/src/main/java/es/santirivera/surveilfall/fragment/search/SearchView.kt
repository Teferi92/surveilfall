package es.santirivera.surveilfall.fragment.search

import android.view.View
import android.widget.EditText
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView

class SearchView(baseActivity: BaseActivity, presenter: SearchListener) : BaseView<SearchListener>(baseActivity, presenter) {

    private var searchEditText: EditText? = null
    private var searchButton: View? = null
    private var randomButton: View? = null


    override fun getContentView(): Int {
        return R.layout.fragment_search
    }

    override fun prepareView() {
        randomButton = mainView.findViewById(R.id.buttonRandom)
        searchButton = mainView.findViewById(R.id.buttonSearch)
        searchEditText = mainView.findViewById(R.id.editTextSearch)

        searchButton!!.setOnClickListener {
            presenter.onSearchClicked(searchEditText!!.text.toString())
        }
        randomButton!!.setOnClickListener {
            presenter.onRandomClicked(searchEditText!!.text.toString())
        }
    }
}
