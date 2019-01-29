package es.santirivera.surveilfall.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject

open class CardList(
        @SerializedName("total_cards")
        var totalCards: Int = 0,
        @SerializedName("data")
        var data: RealmList<Card>? = null,
        @SerializedName("has_more")
        var hasMore: Boolean = false)