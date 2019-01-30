package es.santirivera.surveilfall.fragment.cards.detail

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.Favorite
import es.santirivera.surveilfall.data.model.ImageUris
import io.realm.Realm

class CardDetailFragment : BasePresenter<CardDetailListener>(), CardDetailListener {

    override val titleForActivity: String? get() = card!!.name
    private var view: CardDetailView? = null

    private var addToFavoritesMenu: MenuItem? = null
    private var removeFromFavoritesMenu: MenuItem? = null

    var card: Card? = null

    var favorite = false

    override fun instanceView(): BaseView<*> {
        setHasOptionsMenu(true)
        view = CardDetailView(activity as BaseActivity, this)
        return view as CardDetailView
    }

    override fun loadViewData() {
        isFavorite()
        activity!!.title = card!!.name
        view?.onCardLoaded(card!!)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_detail, menu)
        addToFavoritesMenu = menu.findItem(R.id.action_add_favorite)
        removeFromFavoritesMenu = menu.findItem(R.id.action_remove_favorite)
        removeFromFavoritesMenu!!.isVisible = favorite
        addToFavoritesMenu!!.isVisible = !favorite
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item) {
            addToFavoritesMenu -> {
                addFavorite(card!!)
                true
            }
            removeFromFavoritesMenu -> {
                removeFavorite(card!!)
                true
            }
            else -> false
        }
    }

    override fun onArtistClicked(artist: String) {
        (activity as MainActivity).onArtistClicked(artist)
    }

    override fun shouldShowMenu(): Boolean {
        return false
    }

    override fun toggleFavoriteAction(favorite: Boolean) {
        activity!!.invalidateOptionsMenu()
    }

    override fun onSaveCardArtRequested(uris: ImageUris, cardName:String) {
        (activity as MainActivity).onSaveCardArtRequested(uris, cardName)
    }

    override fun isFavorite() {
        val realm = Realm.getDefaultInstance()
        val favorites = realm.where(Favorite::class.java).equalTo("cardId", card!!.id).findAll()
        favorite = if (favorites.size == 0) {
            false
        } else {
            val fave = favorites[0]
            fave?.isFavorite ?: false
        }
        view!!.setIsFavorite(favorite)
    }

    fun addFavorite(card: Card) {
        favorite = true
        view!!.setIsFavorite(favorite)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val favorite = Favorite(card.id, card, true)
        realm.insertOrUpdate(favorite)
        realm.commitTransaction()

    }

    fun removeFavorite(card: Card) {
        favorite = false
        view!!.setIsFavorite(favorite)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val favorite = Favorite(card.id, card, false)
        realm.insertOrUpdate(favorite)
        realm.commitTransaction()

    }
}