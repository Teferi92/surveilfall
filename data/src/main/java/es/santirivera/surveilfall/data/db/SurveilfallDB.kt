package es.santirivera.surveilfall.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.santirivera.surveilfall.data.db.converter.CardConverter
import es.santirivera.surveilfall.data.db.dao.FavoriteDao
import es.santirivera.surveilfall.data.db.dao.WordBankItemDAO
import es.santirivera.surveilfall.data.model.Favorite
import es.santirivera.surveilfall.data.model.WordBankItem

@Database(entities = [Favorite::class, WordBankItem::class], version = 1)
@TypeConverters(CardConverter::class)
abstract class SurveilfallDB : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun wordBankItemDao(): WordBankItemDAO
}