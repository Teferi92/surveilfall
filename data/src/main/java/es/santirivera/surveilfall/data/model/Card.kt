package es.santirivera.surveilfall.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Card(
        @SerializedName("oracle_text")
        var oracleText: String = "",
        @SerializedName("prints_search_uri")
        var printsSearchUri: String = "",
        @SerializedName("set_search_uri")
        var setSearchUri: String = "",
        @SerializedName("set_name")
        var setName: String = "",
        @SerializedName("scryfall_uri")
        var scryfallUri: String = "",
        @SerializedName("mana_cost")
        var manaCost: String? = "",
        @SerializedName("id")
        @PrimaryKey
        var id: String = "",
        @SerializedName("scryfall_set_uri")
        var scryfallSetUri: String = "",
        @SerializedName("loyalty")
        var loyalty: String? = "",
        @SerializedName("power")
        var power: String? = "",
        @SerializedName("toughness")
        var toughness: String? = "",
        @SerializedName("frame_effect")
        var frameEffect: String = "",
        @SerializedName("type_line")
        var typeLine: String = "",
        @SerializedName("color_identity")
        var colorIdentity: RealmList<String>? = null,
        @SerializedName("legalities")
        var legalities: Legalities? = null,
        @SerializedName("border_color")
        var borderColor: String = "",
        @SerializedName("reserved")
        var reserved: Boolean = false,
        @SerializedName("name")
        var name: String = "",
        @SerializedName("cmc")
        var cmc: Int = 0,
        @SerializedName("arena_id")
        var arenaId: Int = 0,
        @SerializedName("rarity")
        var rarity: String = "",
        @SerializedName("frame")
        var frame: String = "",
        @SerializedName("oracle_id")
        var oracleId: String = "",
        @SerializedName("artist")
        var artist: String = "",
        @SerializedName("released_at")
        var releasedAt: String = "",
        @SerializedName("colors")
        var colors: RealmList<String>? = null,
        @SerializedName("illustration_id")
        var illustrationId: String = "",
        @SerializedName("promo")
        var promo: Boolean = false,
        @SerializedName("mtgo_id")
        var mtgoId: Int = 0,
        @SerializedName("rulings_uri")
        var rulingsUri: String = "",
        @SerializedName("collector_number")
        var collectorNumber: String = "",
        @SerializedName("set_uri")
        var setUri: String = "",
        @SerializedName("lang")
        var lang: String = "",
        @SerializedName("highres_image")
        var highresImage: Boolean = false,
        @SerializedName("digital")
        var digital: Boolean = false,
        @SerializedName("set")
        var set: String = "",
        @SerializedName("reprint")
        var reprint: Boolean = false,
        @SerializedName("full_art")
        var fullArt: Boolean = false,
        @SerializedName("image_uris")
        var imageUris: ImageUris? = null,
        @SerializedName("uri")
        var uri: String = "",
        @SerializedName("layout")
        var layout: String = "",
        @SerializedName("multiverse_ids")
        var multiverseIds: RealmList<Int>? = null,
        @SerializedName("oversized")
        var oversized: Boolean = false,
        @SerializedName("story_spotlight")
        var storySpotlight: Boolean = false,
        @SerializedName("card_faces")
        var cardFaces: RealmList<CardFace>? = null,
        @SerializedName("flavor_text")
        var flavorText: String? = "") : RealmObject() {

    fun toCardDataList(): ArrayList<CardData> {
        val list = ArrayList<CardData>()
        val faces = cardFaces
        if (cardFaces != null && faces!!.isNotEmpty()) {
            for (face in faces) {
                if (face.artist == null || face.artist == "") {
                    face.artist = artist
                }

                val data = CardData(
                        face.name, face.typeLine, face.oracleText,
                        face.loyalty, face.power, face.toughness, face.manaCost,
                        face.flavorText, face.artist, face.imageUris
                )
                list.add(data)
            }
        } else {
            val data = CardData(name, typeLine, oracleText, loyalty, power, toughness, manaCost, flavorText, artist, imageUris)
            list.add(data)
        }
        return list
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Card) {
            return other.id.compareTo(this.id) == 0
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}