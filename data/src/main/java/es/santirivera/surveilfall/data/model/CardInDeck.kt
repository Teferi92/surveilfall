package es.santirivera.surveilfall.data.model

import com.google.gson.annotations.SerializedName

data class CardInDeck(@SerializedName("q") val quantity: String = "",
                      @SerializedName("id") val scryfallId: String = "")