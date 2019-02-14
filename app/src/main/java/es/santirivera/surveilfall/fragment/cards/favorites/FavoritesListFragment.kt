package es.santirivera.surveilfall.fragment.cards.favorites

import android.view.View
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback
import es.santirivera.surveilfall.domain.usecases.implementation.favorite.GetFavoritesUseCase

class FavoritesListFragment : BasePresenter<FavoritesListListener>(), FavoritesListListener {

    override val titleForActivity: String? get() = getString(R.string.favorites)
    private lateinit var view: FavoritesListView

    override fun instanceView(): BaseView<*> {
        view = FavoritesListView(activity as BaseActivity, this)
        return view
    }

    override fun onResume() {
        super.onResume()
        view.resetAdapter()
        loadViewData()
    }

    override fun loadViewData() {
        useCaseHandler.execute(
                useCaseProvider.getFavoritesUseCase,
                object : UseCasePartialCallback<GetFavoritesUseCase.OkOutput, GetFavoritesUseCase.ErrorOutput>() {
                    override fun onSuccess(tag: String?, response: GetFavoritesUseCase.OkOutput) {
                        view.onFavoritesReceived(response.favorites)
                    }
                }
        )
    }

    override fun onCardClicked(card: Card, view: View?) {
        (activity as MainActivity).onCardClicked(card, view)
    }

    override fun shouldShowMenu(): Boolean {
        return true
    }

}