package es.santirivera.surveilfall.adapter

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import es.santirivera.surveilfall.R


class WordBankAdapter(context: Context) : ArrayAdapter<String>(context, R.layout.item_string) {

    private val list = ArrayList<String>()
    private val suggestions = ArrayList<String>()

    private val customFilter = object : Filter() {

        override fun convertResultToString(resultValue: Any): CharSequence {
            return resultValue as String
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values == null) {
                return
            }
            val filteredList: ArrayList<String> = results.values as ArrayList<String>
            if (results.count > 0) {
                clear()
                for (c in filteredList) {
                    add(c)
                }
                notifyDataSetChanged()
            }
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            return if (constraint != null) {
                suggestions.clear()
                for (item in list) {
                    if (item.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(item)
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = suggestions
                filterResults.count = suggestions.size
                filterResults
            } else {
                FilterResults()
            }
        }

    }

    override fun getFilter(): Filter = customFilter

    override fun getItem(position: Int): String? {
        return suggestions[position]
    }

    override fun getCount(): Int {
        return suggestions.size
    }

    fun update(newList: List<String>) {
        list.clear()
        list.addAll(newList)
    }

}
