package es.santirivera.surveilfall.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Card(
        @SerializedName("oracle_text")
        @ColumnInfo(name = "oracle_text")
        val oracleText: String = "",
        @SerializedName("prints_search_uri")
        @ColumnInfo(name = "prints_search_uri")
        val printsSearchUri: String = "",
        @SerializedName("set_search_uri")
        @ColumnInfo(name = "set_search_uri")
        val setSearchUri: String = "",
        @SerializedName("set_name")
        @ColumnInfo(name = "set_name")
        val setName: String = "",
        @SerializedName("scryfall_uri")
        @ColumnInfo(name = "scryfall_uri")
        val scryfallUri: String = "",
        @SerializedName("mana_cost")
        @ColumnInfo(name = "mana_cost")
        val manaCost: String? = "",
        @SerializedName("id")
        @ColumnInfo(name = "id")
        val id: String = "",
        @SerializedName("scryfall_set_uri")
        @ColumnInfo(name = "scryfall_set_uri")
        val scryfallSetUri: String = "",
        @SerializedName("loyalty")
        @ColumnInfo(name = "loyalty")
        val loyalty: String? = "",
        @SerializedName("power")
        @ColumnInfo(name = "power")
        val power: String? = "",
        @SerializedName("toughness")
        @ColumnInfo(name = "toughness")
        val toughness: String? = "",
        @SerializedName("frame_effect")
        @ColumnInfo(name = "frame_effect")
        val frameEffect: String = "",
        @SerializedName("type_line")
        @ColumnInfo(name = "type_line")
        val typeLine: String = "",
        @SerializedName("color_identity")
        @ColumnInfo(name = "color_identity")
        val colorIdentity: ArrayList<String> = ArrayList(),
        @SerializedName("legalities")
        @ColumnInfo(name = "legalities")
        val legalities: Legalities = Legalities(),
        @SerializedName("border_color")
        @ColumnInfo(name = "border_color")
        val borderColor: String = "",
        @SerializedName("reserved")
        @ColumnInfo(name = "reserved")
        val reserved: Boolean = false,
        @SerializedName("name")
        @ColumnInfo(name = "name")
        val name: String = "",
        @SerializedName("cmc")
        @ColumnInfo(name = "cmc")
        val cmc: Int = 0,
        @SerializedName("arena_id")
        @ColumnInfo(name = "arena_id")
        val arenaId: Int = 0,
        @SerializedName("rarity")
        @ColumnInfo(name = "rarity")
        val rarity: String = "",
        @SerializedName("frame")
        @ColumnInfo(name = "frame")
        val frame: String = "",
        @SerializedName("oracle_id")
        @ColumnInfo(name = "oracle_id")
        val oracleId: String = "",
        @SerializedName("artist")
        @ColumnInfo(name = "artist")
        val artist: String = "",
        @SerializedName("released_at")
        @ColumnInfo(name = "released_at")
        val releasedAt: String = "",
        @SerializedName("colors")
        @ColumnInfo(name = "colors")
        val colors: ArrayList<String> = ArrayList(),
        @SerializedName("illustration_id")
        @ColumnInfo(name = "illustration_id")
        val illustrationId: String = "",
        @SerializedName("promo")
        @ColumnInfo(name = "promo")
        val promo: Boolean = false,
        @SerializedName("mtgo_id")
        @ColumnInfo(name = "mtgo_id")
        val mtgoId: Int = 0,
        @SerializedName("rulings_uri")
        @ColumnInfo(name = "rulings_uri")
        val rulingsUri: String = "",
        @SerializedName("collector_number")
        @ColumnInfo(name = "collector_number")
        val collectorNumber: String = "",
        @SerializedName("set_uri")
        @ColumnInfo(name = "set_uri")
        val setUri: String = "",
        @SerializedName("lang")
        @ColumnInfo(name = "lang")
        val lang: String = "",
        @SerializedName("highres_image")
        @ColumnInfo(name = "highres_image")
        val highresImage: Boolean = false,
        @SerializedName("digital")
        @ColumnInfo(name = "digital")
        val digital: Boolean = false,
        @SerializedName("set")
        @ColumnInfo(name = "set")
        val set: String = "",
        @SerializedName("reprint")
        @ColumnInfo(name = "reprint")
        val reprint: Boolean = false,
        @SerializedName("full_art")
        @ColumnInfo(name = "full_art")
        val fullArt: Boolean = false,
        @SerializedName("image_uris")
        @ColumnInfo(name = "image_uris")
        val imageUris: ImageUris = ImageUris(),
        @SerializedName("uri")
        @ColumnInfo(name = "uri")
        val uri: String = "",
        @SerializedName("layout")
        @ColumnInfo(name = "layout")
        val layout: String = "",
        @SerializedName("multiverse_ids")
        @ColumnInfo(name = "multiverse_ids")
        val multiverseIds: ArrayList<Int> = ArrayList(),
        @SerializedName("oversized")
        @ColumnInfo(name = "oversized")
        val oversized: Boolean = false,
        @SerializedName("story_spotlight")
        @ColumnInfo(name = "story_spotlight")
        val storySpotlight: Boolean = false,
        @SerializedName("card_faces")
        @ColumnInfo(name = "card_faces")
        val cardFaces: ArrayList<CardFace> = ArrayList(),
        @SerializedName("flavor_text")
        @ColumnInfo(name = "flavor_text")
        val flavorText: String? = "") {

    fun toCardDataList(): ArrayList<CardData> {
        val list = ArrayList<CardData>()
        val faces = cardFaces
        if (faces.isNotEmpty()) {
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