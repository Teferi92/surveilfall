package es.santirivera.surveilfall.fragment.cards.detail

import android.os.Bundle
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
import es.santirivera.surveilfall.data.model.ImageUris
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback
import es.santirivera.surveilfall.domain.usecases.implementation.favorite.AddFavoriteUseCase
import es.santirivera.surveilfall.domain.usecases.implementation.favorite.IsFavoriteUseCase
import es.santirivera.surveilfall.domain.usecases.implementation.favorite.RemoveFavoriteUseCase

class CardDetailFragment : BasePresenter<CardDetailListener>(), CardDetailListener {

    override val titleForActivity: String? get() = card.name
    private lateinit var view: CardDetailView

    private lateinit var shareMenu: MenuItem
    private lateinit var addToFavoritesMenu: MenuItem
    private lateinit var removeFromFavoritesMenu: MenuItem

    lateinit var card: Card

    var favorite = false


    override fun instanceView(): BaseView<*> {
        setHasOptionsMenu(true)
        view = CardDetailView(activity as BaseActivity, this)
        return view
    }

    override fun loadViewData() {
        isFavorite()
        activity!!.title = card.name
        view.onCardLoaded(card)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.view.setTransitionName(card.id)
        startPostponedEnterTransition()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_detail, menu)
        inflater.inflate(R.menu.menu_share, menu)
        addToFavoritesMenu = menu.findItem(R.id.action_add_favorite)
        removeFromFavoritesMenu = menu.findItem(R.id.action_remove_favorite)
        shareMenu = menu.findItem(R.id.action_share)
        removeFromFavoritesMenu.isVisible = favorite
        addToFavoritesMenu.isVisible = !favorite
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item) {
            addToFavoritesMenu -> {
                addFavorite(card)
                true
            }
            removeFromFavoritesMenu -> {
                removeFavorite(card)
                true
            }
            shareMenu -> {
                (activity as MainActivity).shareText(generateCardAsText())
                true
            }
            else -> false
        }
    }

    private fun generateCardAsText(): String {
        val builder = StringBuilder()
        val faces = card.toCardDataList()
        val faceCount = faces.size
        for ((faceNum, face) in faces.withIndex()) {
            val cardName = face.name
            val cardCost = face.manaCost
            val cardType = face.typeLine
            val cardText = face.oracleText
            val cardPower = face.power
            val cardToughness = face.toughness
            val cardLoyalty = face.loyalty

            builder.append(cardName)
            if (cardCost != null && !cardCost.isEmpty()) {
                builder.append(" (${cardCost.replace("{", "").replace("}", "")})")
            }
            builder.append("\n\n")
            builder.append(cardType)
            builder.append("\n\n")
            builder.append(cardText)
            if (cardPower != null && cardToughness != null && !cardPower.isEmpty() && !cardToughness.isEmpty()) {
                builder.append("\n\n")
                builder.append("$cardPower/$cardToughness")
            } else if (cardLoyalty != null && !cardLoyalty.isEmpty()) {
                builder.append("\n\n")
                builder.append("Loyalty: $cardLoyalty")
            }
            if (faceCount != 1 && faceNum != faceCount - 1) {
                builder.append("\n-------------------\n")
            }
        }
        return builder.toString()
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

    override fun onSaveCardArtRequested(uris: ImageUris, cardName: String) {
        (activity as MainActivity).onSaveCardArtRequested(uris, cardName)
    }

    override fun isFavorite() {
        useCaseHandler.execute(
                useCaseProvider.isFavoriteUseCase,
                IsFavoriteUseCase.Input(card.id),
                object : UseCasePartialCallback<IsFavoriteUseCase.OkOutput, IsFavoriteUseCase.ErrorOutput>() {
                    override fun onSuccess(tag: String, response: IsFavoriteUseCase.OkOutput) {
                        favorite = response.isFavorite
                        view.setIsFavorite(response.isFavorite)
                    }

                }
        )
    }

    private fun addFavorite(card: Card) {
        useCaseHandler.execute(
                useCaseProvider.addFavoriteUseCase,
                AddFavoriteUseCase.Input(card),
                object : UseCasePartialCallback<AddFavoriteUseCase.OkOutput, AddFavoriteUseCase.ErrorOutput>() {
                    override fun onSuccess(tag: String, response: AddFavoriteUseCase.OkOutput) {
                        favorite = true
                        view.setIsFavorite(true)
                    }
                }
        )
    }

    private fun removeFavorite(card: Card) {
        useCaseHandler.execute(
                useCaseProvider.removeFavoriteUseCase,
                RemoveFavoriteUseCase.Input(card),
                object : UseCasePartialCallback<RemoveFavoriteUseCase.OkOutput, RemoveFavoriteUseCase.ErrorOutput>() {
                    override fun onSuccess(tag: String, response: RemoveFavoriteUseCase.OkOutput) {
                        favorite = false
                        view.setIsFavorite(false)
                    }
                }
        )
    }

    override fun onShowReprintsClicked(card: Card?) {
        (activity as MainActivity).onShowReprintsClicked(this.card)
    }


}
