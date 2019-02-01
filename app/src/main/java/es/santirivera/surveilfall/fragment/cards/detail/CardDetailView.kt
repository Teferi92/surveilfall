package es.santirivera.surveilfall.fragment.cards.detail

import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.CardDetailsAdapter
import es.santirivera.surveilfall.adapter.LegalityAdapter
import es.santirivera.surveilfall.adapter.viewholder.CardDataViewHolder
import es.santirivera.surveilfall.base.activity.BaseActivity
import es.santirivera.surveilfall.base.view.BaseView
import es.santirivera.surveilfall.data.model.Card
import es.santirivera.surveilfall.data.model.CardData

class CardDetailView(baseActivity: BaseActivity, presenter: CardDetailListener) : BaseView<CardDetailListener>(baseActivity, presenter), CardDataViewHolder.OnArtistClickedListener {

    private var imageViewCard: ImageView? = null
    private var recyclerViewCardData: RecyclerView? = null
    private var recyclerViewLegality: RecyclerView? = null
    private var fab: FloatingActionButton? = null
    private var showReprintsButton: Button? = null

    private var currentFace = 0
    private var cardDataList: ArrayList<CardData>? = null

    override val contentView: Int get() = R.layout.fragment_card_detail

    override fun prepareView() {
        imageViewCard = mainView?.findViewById(R.id.imageViewCardImage)
        recyclerViewCardData = mainView?.findViewById(R.id.recyclerViewCardData)
        recyclerViewLegality = mainView?.findViewById(R.id.recyclerViewLegality)
        fab = mainView?.findViewById(R.id.fabFlipCard)
        showReprintsButton = mainView?.findViewById(R.id.buttonShowReprints)
    }

    fun onCardLoaded(card: Card) {
        cardDataList = card.toCardDataList()
        val requestOptions = RequestOptions().placeholder(R.drawable.placeholder)

        if (card.imageUris != null) {
            // Only one face
            Glide.with(imageViewCard as ImageView).load(card.imageUris!!.png).apply(requestOptions).into(imageViewCard as ImageView)
            imageViewCard!!.setOnLongClickListener {
                presenter.onSaveCardArtRequested(card.imageUris!!, card.name)
                true
            }
            fab!!.hide()
        } else {
            // Two faces
            Glide.with(imageViewCard as ImageView).load(cardDataList!![0].imageUris!!.png).apply(requestOptions).into(imageViewCard as ImageView)
            imageViewCard!!.setOnLongClickListener {
                presenter.onSaveCardArtRequested(cardDataList!![currentFace].imageUris!!, cardDataList!![currentFace].name!!)
                true
            }
            fab!!.show()
            fab!!.setOnClickListener {
                currentFace = if (currentFace == 1) {
                    fab!!.setImageResource(R.drawable.ic_back_side)
                    0
                } else {
                    fab!!.setImageResource(R.drawable.ic_front_side)
                    1
                }
                Glide.with(imageViewCard as ImageView).load(cardDataList!![currentFace].imageUris!!.png).apply(requestOptions).into(imageViewCard as ImageView)

            }
        }

        showReprintsButton!!.setOnClickListener {
            presenter.onShowReprintsClicked(null)
        }

        recyclerViewCardData!!.layoutManager = LinearLayoutManager(baseActivity)
        recyclerViewCardData!!.adapter = CardDetailsAdapter(cardDataList!!, this)

        recyclerViewLegality!!.layoutManager = GridLayoutManager(baseActivity, 2)
        recyclerViewLegality!!.adapter = LegalityAdapter(card.legalities!!.toLegalityList())
    }

    override fun onArtistClicked(artist: String) {
        presenter.onArtistClicked(artist)
    }

    fun setIsFavorite(favorite: Boolean) {
        presenter.toggleFavoriteAction(favorite)
    }


}
