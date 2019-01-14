package es.santirivera.surveilfall.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.adapter.viewholder.StringViewHolder

class SimpleStringAdapter(val strings: List<String>, val listener: StringViewHolder.OnStringClickedListener) : RecyclerView.Adapter<StringViewHolder>() {

    override fun getItemCount(): Int {
        return strings.size
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.bind(strings[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_string, parent, false)
        return StringViewHolder(view, listener)
    }
}