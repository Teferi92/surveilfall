package es.santirivera.surveilfall.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.data.model.Format

class FormatViewHolder(itemView: View, private val listener: OnFormatClickedListener) : RecyclerView.ViewHolder(itemView) {

    interface OnFormatClickedListener {
        fun onFormatClicked(format: Format, view: View)
    }

    private val requestBuilder = Glide.with(itemView)
    private val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
    private val imageViewIcon: ImageView = itemView.findViewById(R.id.imageViewIcon)

    fun bind(format: Format) {
        itemView.setOnClickListener {
            listener.onFormatClicked(format, it)
        }
        textViewTitle.text = format.n.capitalize()
        requestBuilder.load(format.image).into(imageViewIcon)
    }

}