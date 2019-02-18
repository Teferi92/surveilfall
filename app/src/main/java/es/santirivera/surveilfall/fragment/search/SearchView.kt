package es.santirivera.surveilfall.fragment.search

import android.animation.ObjectAnimator
import android.transition.TransitionManager
import android.view.KeyEvent
import android.widget.TextView
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView
import androidx.constraintlayout.widget.ConstraintSet
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.WordBankAdapter
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.WordBankItem
import es.santirivera.surveilfall.util.layout.SurveilfallConstraintLayout
import es.santirivera.surveilfall.util.tokenizer.SpaceTokenizer

class SearchView(baseActivity: BaseActivity, presenter: SearchListener) : BaseView<SearchListener>(baseActivity, presenter), TextView.OnEditorActionListener, SurveilfallConstraintLayout.OnBackPressedListener {


    override val contentView: Int get() = R.layout.fragment_search

    private lateinit var searchEditText: AppCompatMultiAutoCompleteTextView
    private lateinit var constraintLayout: SurveilfallConstraintLayout

    private lateinit var constraintSet1: ConstraintSet
    private lateinit var constraintSet2: ConstraintSet

    private val wordBankAdapter = WordBankAdapter(baseActivity)

    private val values = arrayOf(
            KeyEvent.KEYCODE_CALL,
            KeyEvent.KEYCODE_DPAD_CENTER,
            KeyEvent.KEYCODE_ENTER,
            KeyEvent.KEYCODE_NUMPAD_ENTER,
            KeyEvent.KEYCODE_SEARCH
    )

    private var center = true

    private var newQuery = ""

    override fun prepareView() {
        constraintLayout = mainView.findViewById(R.id.constraintLayout)
        constraintLayout.onBackPressedListener = this

        constraintSet1 = ConstraintSet()
        constraintSet1.clone(constraintLayout)
        constraintSet2 = ConstraintSet()
        constraintSet2.clone(baseActivity, R.layout.fragment_search_open)

        searchEditText = mainView.findViewById(R.id.editTextSearch)
        searchEditText.setOnEditorActionListener(this)
        searchEditText.setAdapter(wordBankAdapter)
        searchEditText.setTokenizer(SpaceTokenizer())

        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                animateToTop()
            } else {
                animateToCenter()
                baseActivity.hideKeyboard(searchEditText)
            }
        }
        constraintLayout.setOnClickListener {
            clearFocus()
        }
    }

    private fun animateToTop() {
        if (center) {
            center = false
            TransitionManager.beginDelayedTransition(constraintLayout)
            val constraint = constraintSet2
            constraint.applyTo(constraintLayout)
        }
    }

    private fun animateToCenter() {
        if (!center) {
            center = true
            TransitionManager.beginDelayedTransition(constraintLayout)
            val constraint = constraintSet1
            constraint.applyTo(constraintLayout)
        }
    }

    private fun performSearch() {
        presenter.onSearchClicked(searchEditText.text.toString(), presenter)
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
        searchEditText.setText(newQuery)
    }

    fun onWordBankReceived(search: List<WordBankItem>) {
        val list = ArrayList<String>()
        for (item in search) {
            list.add(item.name)
        }
        wordBankAdapter.update(list)
    }

    fun clearFocus() {
        searchEditText.let {
            searchEditText.clearFocus()
            baseActivity.hideKeyboard(searchEditText)
        }
    }

    override fun onBackPressed() {
        clearFocus()
    }
}
