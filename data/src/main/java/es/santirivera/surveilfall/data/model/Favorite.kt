package es.santirivera.surveilfall.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Favorite(
        @PrimaryKey
        var cardId: String? = null,
        var card: Card? = null,
        var isFavorite: Boolean = false) : RealmObject()