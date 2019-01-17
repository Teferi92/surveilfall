package es.santirivera.surveilfall.fragment.cards.detail

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.LegalityAdapter
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card

class CardDetailView(baseActivity: BaseActivity, presenter: CardDetailListener) : BaseView<CardDetailListener>(baseActivity, presenter) {

    private var imageViewCard: ImageView? = null
    private var textViewCardText: TextView? = null
    private var textViewCardType: TextView? = null
    private var textViewCardName: TextView? = null
    private var textViewCardArtist: TextView? = null
    private var textViewCardFlavor: TextView? = null
    private var textViewCardCost: TextView? = null
    private var textViewCardStats: TextView? = null
    private var recyclerViewLegality: RecyclerView? = null

    override fun getContentView(): Int {
        return R.layout.fragment_card_detail
    }

    override fun prepareView() {
        imageViewCard = mainView.findViewById(R.id.imageViewCardImage)
        textViewCardText = mainView.findViewById(R.id.textViewCardText)
        textViewCardType = mainView.findViewById(R.id.textViewCardType)
        textViewCardName = mainView.findViewById(R.id.textViewCardName)
        textViewCardFlavor = mainView.findViewById(R.id.textViewCardFlavor)
        textViewCardArtist = mainView.findViewById(R.id.textViewArtist)
        textViewCardCost = mainView.findViewById(R.id.textViewCardCost)
        textViewCardStats = mainView.findViewById(R.id.textViewCardStats)
        recyclerViewLegality = mainView.findViewById(R.id.recyclerViewLegality)
    }

    fun onCardLoaded(card: Card) {
        Glide.with(imageViewCard as ImageView).load(card.imageUris.png).into(imageViewCard as ImageView)
        textViewCardName!!.text = card.name
        textViewCardType!!.text = card.typeLine
        textViewCardText!!.text = card.oracleText

        if (card.power != null && !card.power!!.isEmpty()) {
            // Creature
            textViewCardStats!!.visibility = View.VISIBLE
            textViewCardStats!!.text = mainView.resources.getString(R.string.power_toughness, card.power, card.toughness)
        } else if (card.loyalty != null && !card.loyalty!!.isEmpty()) {
            // Planeswalker
            textViewCardStats!!.visibility = View.VISIBLE
            textViewCardStats!!.text = mainView.resources.getString(R.string.loyalty, card.loyalty)
        } else {
            // Other
            textViewCardStats!!.visibility = View.GONE
        }

        if (card.manaCost != null && (!card.manaCost!!.isEmpty())) {
            textViewCardCost!!.visibility = View.VISIBLE
            textViewCardCost!!.text = card.manaCost!!.replace("{", "").replace("}", "")
        } else {
            textViewCardCost!!.visibility = View.GONE
        }
        if (card.flavorText != null && (!card.flavorText!!.isEmpty())) {
            textViewCardFlavor!!.visibility = View.VISIBLE
            textViewCardFlavor!!.text = card.flavorText
        } else {
            textViewCardFlavor!!.visibility = View.GONE
        }
        textViewCardArtist!!.text = mainView.resources.getString(R.string.illustration, card.artist)
        textViewCardArtist!!.setOnClickListener { presenter.onArtistClicked(card.artist) }

        recyclerViewLegality!!.layoutManager = GridLayoutManager(baseActivity, 2)
        recyclerViewLegality!!.adapter = LegalityAdapter(card.legalities.toLegalityList())
    }
}
