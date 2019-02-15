package es.santirivera.surveilfall.fragment.challenges.deck

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.activity.MainActivity
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.presenter.BasePresenter
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.CardInDeck
import es.santirivera.surveilfall.data.model.Deck
import es.santirivera.surveilfall.domain.usecases.base.UseCasePartialCallback
import es.santirivera.surveilfall.domain.usecases.implementation.cards.GetCardCollectionUseCase

class DeckListDetailFragment : BasePresenter<DeckListDetailListener>(), DeckListDetailListener {

    lateinit var deck: Deck
    lateinit var list: List<Card>
    lateinit var title: String
    lateinit var view: DeckListDetailView
    private lateinit var menuReload: MenuItem
    private lateinit var menuShare: MenuItem

    override val titleForActivity: String
        get() = title

    override fun instanceView(): BaseView<*> {
        setHasOptionsMenu(true)
        view = DeckListDetailView(activity as BaseActivity, this)
        return view
    }

    override fun loadViewData() {
        useCaseHandler.execute(
                useCaseProvider.getCardCollectionUseCase,
                GetCardCollectionUseCase.Input(deck.getCardIdentifiers()),
                object : UseCasePartialCallback<GetCardCollectionUseCase.OkOutput, GetCardCollectionUseCase.ErrorOutput>() {
                    override fun onSuccess(tag: String, response: GetCardCollectionUseCase.OkOutput) {
                        list = response.cardList
                        view.onDataReceived(deck, list)
                        menuShare.isVisible = true
                    }
                }
        )
    }

    override fun shouldShowMenu(): Boolean {
        return false
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_reload, menu)
        inflater.inflate(R.menu.menu_share, menu)
        menuReload = menu.findItem(R.id.action_reload)
        menuShare = menu.findItem(R.id.action_share)
        menuShare.isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item) {
            menuReload -> {
                loadViewData()
                true
            }
            menuShare -> {
                (activity as MainActivity).shareText(generateDeckAsText())
                true
            }
            else -> false
        }
    }

    private fun generateDeckAsText(): String {
        val builder = StringBuilder()
        val mainDeck = deck.maindeck as ArrayList<CardInDeck>
        val sideboard = deck.sideboard as ArrayList<CardInDeck>
        val map = HashMap<String, Card>()
        for (card in list) {
            map[card.id] = card
        }
        val comparator = Comparator { o1: CardInDeck, o2: CardInDeck ->
            val card1 = map[o1.scryfallId]!!
            val card2 = map[o2.scryfallId]!!

            val type1 = card1.typeLine
            val type2 = card2.typeLine
            val cmc1 = card1.cmc
            val cmc2 = card2.cmc
            val name1 = card1.name
            val name2 = card2.name
            if (type1.contains("Creature")) {
                if (type2.contains("Creature")) {
                    cmc1 - cmc2
                } else {
                    -1
                }
            } else if (type2.contains("Creature")) {
                1
            } else if (type1.contains("Planeswalker")) {
                if (type2.contains("Planeswalker")) {
                    cmc1 - cmc2
                } else {
                    -1
                }
            } else if (type2.contains("Planeswalker")) {
                1
            } else if (type1.contains("Instant") || type1.contains("Sorcery")) {
                if (type2.contains("Instant") || type2.contains("Sorcery")) {
                    cmc1 - cmc2
                } else {
                    -1
                }
            } else if (type2.contains("Instant") || type2.contains("Sorcery")) {
                1
            } else if (type1.contains("Artifact")) {
                if (type2.contains("Artifact")) {
                    cmc1 - cmc2
                } else {
                    -1
                }
            } else if (type2.contains("Artifact")) {
                1
            } else if (type1.contains("Enchantment")) {
                if (type2.contains("Enchantment")) {
                    cmc1 - cmc2
                } else {
                    -1
                }
            } else if (type2.contains("Enchantment")) {
                1
            } else {
                name1.compareTo(name2)
            }
        }
        mainDeck.sortWith(comparator)
        sideboard.sortWith(comparator)
        builder.append("${deck.name} by ${deck.player} (${deck.wins}-${deck.losses})\n\n")
        builder.append("Main deck: \n\n")
        for (card in mainDeck) {
            val q = card.quantity
            val n = map[card.scryfallId]!!.name
            builder.append("${q}x $n\n")
        }
        builder.append("\nSideboard: \n\n")
        for (card in sideboard) {
            val q = card.quantity
            val n = map[card.scryfallId]!!.name
            builder.append("${q}x $n\n")
        }
        val string = builder.toString()
        return string.substring(0, string.length - 1)
    }

    override fun onCardClicked(card: Card) {
        (activity as MainActivity).onCardClicked(card, null)
    }
}
