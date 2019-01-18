package es.santirivera.surveilfall.fragment.cards.detail

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.CardDetailsAdapter
import es.santirivera.surveilfall.adapter.LegalityAdapter
import es.santirivera.surveilfall.adapter.viewholder.CardDataViewHolder
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card

class CardDetailView(baseActivity: BaseActivity, presenter: CardDetailListener) : BaseView<CardDetailListener>(baseActivity, presenter), CardDataViewHolder.OnArtistClickedListener {

    private var imageViewCard: ImageView? = null
    private var recyclerViewCardData: RecyclerView? = null
    private var recyclerViewLegality: RecyclerView? = null

    override fun getContentView(): Int {
        return R.layout.fragment_card_detail
    }

    override fun prepareView() {
        imageViewCard = mainView.findViewById(R.id.imageViewCardImage)
        recyclerViewCardData = mainView.findViewById(R.id.recyclerViewCardData)
        recyclerViewLegality = mainView.findViewById(R.id.recyclerViewLegality)
    }

    fun onCardLoaded(card: Card) {
        Glide.with(imageViewCard as ImageView).load(card.imageUris?.png).into(imageViewCard as ImageView)

        recyclerViewCardData!!.layoutManager = LinearLayoutManager(baseActivity)
        recyclerViewCardData!!.adapter = CardDetailsAdapter(card.toCardDataList(), this)

        recyclerViewLegality!!.layoutManager = GridLayoutManager(baseActivity, 2)
        recyclerViewLegality!!.adapter = LegalityAdapter(card.legalities.toLegalityList())
    }

    override fun onArtistClicked(artist: String) {
        presenter.onArtistClicked(artist)
    }

}
