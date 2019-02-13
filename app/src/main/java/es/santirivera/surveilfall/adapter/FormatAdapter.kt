package es.santirivera.surveilfall.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.viewholder.FormatViewHolder
import es.santirivera.surveilfall.data.model.Format

class FormatAdapter(val listener: FormatViewHolder.OnFormatClickedListener) : RecyclerView.Adapter<FormatViewHolder>() {

    private val formats: ArrayList<Format> = ArrayList()

    override fun getItemCount(): Int {
        return formats.size
    }

    override fun onBindViewHolder(holder: FormatViewHolder, position: Int) {
        holder.bind(formats[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormatViewHolder {
        val view = View.inflate(parent.context, R.layout.item_format, null)
        return FormatViewHolder(view, listener)
    }

    fun addFormats(formats: List<Format>){
        this.formats.clear()
        for(format in formats){
            this.formats.add(format)
        }
        notifyDataSetChanged()
    }

}