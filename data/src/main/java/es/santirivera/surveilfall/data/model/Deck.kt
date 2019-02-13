package es.santirivera.surveilfall.data.model

import com.google.gson.Gson

data class Deck(val p: Int,
                val s: List<CardInDeck>,
                val w: String,
                val l: String,
                val pl: String,
                val m: List<CardInDeck>,
                val n: String) {

    fun getCardIds(): String {
        val ids = ArrayList<String>()
        for (card in m){
            if (!ids.contains(card.id)){
                ids.add(card.id)
            }
        }
        for (card in s){
            if (!ids.contains(card.id)){
                ids.add(card.id)
            }
        }
        return Gson().toJson(ids)
    }

    fun getSize() : Int {
        return m.size + s.size
    }

}