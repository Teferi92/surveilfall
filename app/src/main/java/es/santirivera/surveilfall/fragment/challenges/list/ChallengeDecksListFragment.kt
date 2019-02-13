package es.santirivera.surveilfall.fragment.challenges.list

import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Deck
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback
import es.santirivera.surveilfall.domain.usecases.implementation.tournament.GetTournamentUseCase

class ChallengeDecksListFragment : BasePresenter<ChallengeDecksListListener>(), ChallengeDecksListListener {


    lateinit var date: String
    lateinit var format: String
    private lateinit var view: ChallengeDecksListView

    override val titleForActivity: String?
        get() = getString(R.string.challenge, format, date).capitalize()

    override fun instanceView(): BaseView<*> {
        view = ChallengeDecksListView(activity as BaseActivity, this)
        return view
    }

    override fun loadViewData() {
        useCaseHandler.execute(
                useCaseProvider.getTournamentUseCase,
                GetTournamentUseCase.Input(format, date),
                object : UseCasePartialCallback<GetTournamentUseCase.OkOutput, GetTournamentUseCase.ErrorOutput>() {
                    override fun onSuccess(tag: String?, response: GetTournamentUseCase.OkOutput) {
                        view.onTournamentLoaded(response.tournament)
                    }
                }
        )
    }

    override fun shouldShowMenu(): Boolean {
        return false
    }

    override fun onDeckClicked(deck: Deck) {
        (activity as MainActivity).onDeckClicked(deck)
    }

}
