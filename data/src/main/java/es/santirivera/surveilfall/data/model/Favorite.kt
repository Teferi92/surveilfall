package es.santirivera.surveilfall.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import es.santirivera.surveilfall.data.db.converter.CardConverter

@Entity
data class Favorite(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "card_id")
        val cardId: String = "",
        @ColumnInfo(name = "card")
        val card: Card,
        @ColumnInfo(name = "is_favorite")
        val isFavorite: Boolean = false)