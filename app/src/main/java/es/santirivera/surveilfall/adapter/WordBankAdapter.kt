package es.santirivera.surveilfall.adapter

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import es.santirivera.surveilfall.R
import es.santirivera.surveilfall.data.model.WordBankItem


class WordBankAdapter(context: Context) : ArrayAdapter<WordBankItem>(context, R.layout.item_string) {

    private val list = ArrayList<WordBankItem>()
    private val suggestions = ArrayList<WordBankItem>()

    private val customFilter = object : Filter() {

        override fun convertResultToString(resultValue: Any): CharSequence {
            return (resultValue as WordBankItem).name
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values == null) {
                return
            }
            val filteredList: ArrayList<WordBankItem> = results.values as ArrayList<WordBankItem>
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
                for (wordBankItem in list) {
                    if (wordBankItem.name.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(wordBankItem)
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

    override fun getItem(position: Int): WordBankItem? {
        return suggestions[position]
    }

    override fun getCount(): Int {
        return suggestions.size
    }

    fun update(newList: List<WordBankItem>) {
        list.clear()
        list.addAll(newList)
    }

}
