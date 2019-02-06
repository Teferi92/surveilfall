package es.santirivera.surveilfall.fragment.momir

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.domain.usecases.implementation.cards.GetRandomCardUseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback

class MomirFragment : BasePresenter<MomirListener>(), MomirListener {

    override val titleForActivity: String? get() = getString(R.string.momir)

    private var view: MomirView? = null
    private var help: MenuItem? = null

    override fun instanceView(): BaseView<*> {
        setHasOptionsMenu(true)
        view = MomirView(activity as BaseActivity, this)
        return view as MomirView
    }

    override fun loadViewData() {
        // Do nothing
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        help = menu.add(R.string.momir_help)
        help?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        help?.setIcon(R.drawable.ic_help)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item == help) {
            view?.showHelp()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun shouldShowMenu(): Boolean {
        return true
    }

    override fun onCardClicked(card: Card, view: View) {
        (activity as MainActivity).onCardClicked(card, view)
    }

    override fun onCardRequested(cmc: Int) {
        // Dialog to determine the cmc
        val useCase = useCaseProvider?.getRandomCardUseCase
        val input = GetRandomCardUseCase.Input("t:creature -is:funny cmc:$cmc")
        useCaseHandler?.execute(useCase, input, MomirCallback())
    }

    inner class MomirCallback : UseCasePartialCallback<GetRandomCardUseCase.OkOutput, GetRandomCardUseCase.ErrorOutput>() {
        override fun isReady(): Boolean {
            return true
        }

        override fun onSuccess(tag: String, response: GetRandomCardUseCase.OkOutput) {
            view!!.onCardReceived(response.card)
        }
    }


}