package es.santirivera.surveilfall.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.viewholder.SetViewHolder
import es.santirivera.surveilfall.data.model.Set

class SetAdapter(val sets: List<Set>, val listener: SetViewHolder.OnSetClickedListener) : RecyclerView.Adapter<SetViewHolder>() {


    override fun getItemCount(): Int {
        return sets.size
    }

    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        holder.bind(sets[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        val view = View.inflate(parent.context, R.layout.item_set, null)
        return SetViewHolder(view, listener)
    }
}