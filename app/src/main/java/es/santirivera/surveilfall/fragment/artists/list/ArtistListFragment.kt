package es.santirivera.surveilfall.fragment.artists.list

import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.domain.usecases.implementation.artist.GetArtistNamesUseCase
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback


class ArtistListFragment : BasePresenter<ArtistListListener>(), ArtistListListener {

    override val titleForActivity: String? get() = getString(R.string.artists)

    private var view: ArtistListView? = null

    override fun instanceView(): BaseView<*> {
        view = ArtistListView(activity as BaseActivity, this)
        return view as ArtistListView
    }

    override fun loadViewData() {
        useCaseHandler?.execute(useCaseProvider?.getArtistNamesUseCase, ArtistCallback())
    }

    override fun onArtistClicked(artist: String) {
        (activity as MainActivity).onArtistClicked(artist)
    }


    override fun shouldShowMenu(): Boolean {
        return true
    }


    private inner class ArtistCallback : UseCasePartialCallback<GetArtistNamesUseCase.OkOutput, GetArtistNamesUseCase.ErrorOutput>() {

        override fun isReady(): Boolean {
            return true
        }

        override fun onSuccess(tag: String?, response: GetArtistNamesUseCase.OkOutput) {
            view!!.onArtistsReceived(response.artists)
        }
    }

}
