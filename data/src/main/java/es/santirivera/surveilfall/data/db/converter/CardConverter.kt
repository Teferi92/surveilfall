package es.santirivera.surveilfall.data.db.converter

import com.google.gson.Gson

import androidx.room.TypeConverter
import es.santirivera.surveilfall.data.model.Card
import com.google.gson.reflect.TypeToken
import java.util.*
import java.util.Collections.emptyList


class CardConverter {

    @TypeConverter
    fun fromCard(card: Card): String {
        return gson.toJson(card)
    }

    @TypeConverter
    fun fromString(str: String): Card {
        return gson.fromJson(str, Card::class.java)
    }

    @TypeConverter
    fun storedStringToMyObjects(data: String?): List<Card> {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Card>>() {

        }.type
        return gson.fromJson<List<Card>>(data, listType)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: List<Card>): String {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

    companion object {
        private val gson = Gson()
    }

}
