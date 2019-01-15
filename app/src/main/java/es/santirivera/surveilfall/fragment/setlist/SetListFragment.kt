package es.santirivera.surveilfall.fragment.setlist

import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Set
import es.santirivera.surveilfall.domain.usecases.GetSetsUseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback

class SetListFragment : BasePresenter<SetListListener>(), SetListListener {


    private var view: SetListView? = null
    private val setCallback: SetCallback = SetCallback()

    override fun instanceView(): BaseView<*> {
        view = SetListView(activity as BaseActivity, this)
        return view as SetListView
    }

    override fun loadViewData() {
        useCaseHandler.execute<GetSetsUseCase.OkOutput, GetSetsUseCase.ErrorOutput>(useCaseProvider.getSetsUseCase, setCallback)
    }

    override fun onSetClicked(set: Set) {
        (activity as MainActivity).onSetClicked(set)
    }

    override fun getTitleForActivity(): String {
        return getString(R.string.sets)
    }

    private inner class SetCallback : UseCasePartialCallback<GetSetsUseCase.OkOutput, GetSetsUseCase.ErrorOutput>() {

        override fun isReady(): Boolean {
            return true
        }

        override fun onSuccess(tag: String?, response: GetSetsUseCase.OkOutput) {
            view!!.onSetsReceived(response.sets)
        }
    }
}