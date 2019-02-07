package es.santirivera.surveilfall.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.viewholder.MomirCMCViewHolder

class MomirCMCAdapter(private val strings: List<String>, val listener: MomirCMCViewHolder.OnStringClickedListener) : RecyclerView.Adapter<MomirCMCViewHolder>() {

    override fun getItemCount(): Int {
        return strings.size
    }

    override fun onBindViewHolder(holder: MomirCMCViewHolder, position: Int) {
        holder.bind(strings[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomirCMCViewHolder {
        val layout = R.layout.item_string_momir
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return MomirCMCViewHolder(view, listener)
    }
}