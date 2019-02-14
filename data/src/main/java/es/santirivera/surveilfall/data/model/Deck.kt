package es.santirivera.surveilfall.data.model

import com.google.gson.annotations.SerializedName

data class Deck(@SerializedName("p") val position: Int,
                @SerializedName("s") val sideboard: List<CardInDeck>,
                @SerializedName("w") val wins: String,
                @SerializedName("l") val losses: String,
                @SerializedName("pl") val player: String,
                @SerializedName("m") val maindeck: List<CardInDeck>,
                @SerializedName("n") val name: String) {

    fun getCardIdentifiers(): List<Identifier> {
        val ids = ArrayList<Identifier>()
        for (card in maindeck) {
            val identifier = Identifier(id = card.scryfallId)
            if (!ids.contains(identifier)) {
                ids.add(identifier)
            }
        }
        for (card in sideboard) {
            val identifier = Identifier(id = card.scryfallId)
            if (!ids.contains(identifier)) {
                ids.add(identifier)
            }
        }
        return ids
    }

    fun getSize(): Int {
        return maindeck.size + sideboard.size
    }

}