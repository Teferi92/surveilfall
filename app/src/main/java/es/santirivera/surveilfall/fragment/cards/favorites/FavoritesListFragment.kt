package es.santirivera.surveilfall.fragment.cards.favorites

import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.Favorite
import io.realm.Realm
import io.realm.RealmChangeListener


class FavoritesListFragment : BasePresenter<FavoritesListListener>(), FavoritesListListener {

    override val titleForActivity: String? get() = getString(R.string.favorites)
    private var view: FavoritesListView? = null

    override fun instanceView(): BaseView<*> {
        view = FavoritesListView(activity as BaseActivity, this)
        return view as FavoritesListView
    }

    override fun loadViewData() {
        val realm = Realm.getDefaultInstance()
        val search = realm.where(Favorite::class.java).equalTo("isFavorite", true).findAllAsync()
        view?.onFavoritesReceived(search)
        search.addChangeListener(RealmChangeListener {
            view?.onFavoritesReceived(it)
        })
    }

    override fun onCardClicked(card: Card) {
        (activity as MainActivity).onCardClicked(card)
    }

    override fun shouldShowMenu(): Boolean {
        return true
    }

}