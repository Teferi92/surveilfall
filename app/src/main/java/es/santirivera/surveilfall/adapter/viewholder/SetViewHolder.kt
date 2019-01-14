package es.santirivera.surveilfall.adapter.viewholder

import android.graphics.drawable.PictureDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.data.model.Set
import es.santirivera.surveilfall.util.svg.SvgSoftwareLayerSetter

class SetViewHolder(itemView: View, val listener: OnSetClickedListener) : RecyclerView.ViewHolder(itemView) {

    interface OnSetClickedListener {
        fun onSetClicked(set: Set)
    }

    private val requestBuilder = Glide.with(itemView).`as`(PictureDrawable::class.java).listener(SvgSoftwareLayerSetter())
    private val textViewSetName: TextView = itemView.findViewById(R.id.textViewTitle)
    private val imageViewIcon: ImageView = itemView.findViewById(R.id.imageViewIcon)

    fun bind(set: Set) {
        textViewSetName.text = set.name
        itemView.setOnClickListener {
            listener.onSetClicked(set)
        }
        requestBuilder.load(set.iconUri).into(imageViewIcon)
    }

}
