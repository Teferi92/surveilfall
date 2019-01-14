package es.santirivera.surveilfall.data.model

import com.google.gson.annotations.SerializedName

data class CardList(
        @SerializedName("total_cards")
        val totalCards: Int = 0,
        @SerializedName("data")
        val data: List<Card>?,
        @SerializedName("has_more")
        val hasMore: Boolean = false)