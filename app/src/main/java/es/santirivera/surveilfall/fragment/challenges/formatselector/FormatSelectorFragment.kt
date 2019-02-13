package es.santirivera.surveilfall.fragment.challenges.formatselector

import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback
import es.santirivera.surveilfall.domain.usecases.implementation.tournament.GetFormatsUseCase

class FormatSelectorFragment : BasePresenter<FormatSelectorListener>(), FormatSelectorListener {

    private lateinit var view: FormatSelectorView

    override val titleForActivity: String?
        get() = getString(R.string.challenges)

    override fun instanceView(): BaseView<*> {
        view = FormatSelectorView(activity as BaseActivity, this)
        return view
    }

    override fun loadViewData() {
        useCaseHandler.execute(useCaseProvider.getFormatsUseCase, object : UseCasePartialCallback<GetFormatsUseCase.OkOutput, GetFormatsUseCase.ErrorOutput>() {
            override fun onSuccess(tag: String?, response: GetFormatsUseCase.OkOutput) {
                view.onFormatsLoaded(response.urls)
            }
        })
    }

    override fun shouldShowMenu(): Boolean {
        return true
    }

    override fun onFormatSelected(format: String) {
        (activity as MainActivity).onFormatSelected(format)
    }

}