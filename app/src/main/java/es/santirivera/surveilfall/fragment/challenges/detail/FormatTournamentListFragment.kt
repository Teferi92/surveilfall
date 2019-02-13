package es.santirivera.surveilfall.fragment.challenges.detail

import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback
import es.santirivera.surveilfall.domain.usecases.implementation.tournament.GetTournamentURLsUseCase

class FormatTournamentListFragment : BasePresenter<FormatTournamentListListener>(), FormatTournamentListListener {

    lateinit var format: String
    lateinit var view: FormatTournamentListView

    override val titleForActivity: String?
        get() = format.capitalize()

    override fun instanceView(): BaseView<*> {
        view = FormatTournamentListView(activity as BaseActivity, this)
        view.format = format
        return view
    }

    override fun loadViewData() {
        useCaseHandler.execute(
                useCaseProvider.getTournamentURLUseCase,
                object : UseCasePartialCallback<GetTournamentURLsUseCase.OkOutput, GetTournamentURLsUseCase.ErrorOutput>() {
                    override fun onSuccess(tag: String?, response: GetTournamentURLsUseCase.OkOutput) {
                        when (format) {
                            "modern" -> view.onTournamentsLoaded(response.urls.modern)
                            "legacy" -> view.onTournamentsLoaded(response.urls.legacy)
                            "vintage" -> view.onTournamentsLoaded(response.urls.vintage)
                            "pauper" -> view.onTournamentsLoaded(response.urls.pauper)
                        }
                    }
                })
    }

    override fun shouldShowMenu(): Boolean {
        return false
    }

    override fun onTournamentClicked(format: String, date: String) {
        (activity as MainActivity).onTournamentClicked(format, date)
    }
}
