package es.santirivera.surveilfall.data.model

import com.google.gson.annotations.SerializedName

data class Card(
        @SerializedName("oracle_text")
        val oracleText: String = "",
        @SerializedName("prints_search_uri")
        val printsSearchUri: String = "",
        @SerializedName("set_search_uri")
        val setSearchUri: String = "",
        @SerializedName("set_name")
        val setName: String = "",
        @SerializedName("scryfall_uri")
        val scryfallUri: String = "",
        @SerializedName("mana_cost")
        val manaCost: String? = "",
        @SerializedName("id")
        val id: String = "",
        @SerializedName("scryfall_set_uri")
        val scryfallSetUri: String = "",
        @SerializedName("loyalty")
        val loyalty: String? = "",
        @SerializedName("power")
        val power: String? = "",
        @SerializedName("toughness")
        val toughness: String? = "",
        @SerializedName("frame_effect")
        val frameEffect: String = "",
        @SerializedName("type_line")
        val typeLine: String = "",
        @SerializedName("color_identity")
        val colorIdentity: List<String>?,
        @SerializedName("legalities")
        val legalities: Legalities,
        @SerializedName("border_color")
        val borderColor: String = "",
        @SerializedName("reserved")
        val reserved: Boolean = false,
        @SerializedName("name")
        val name: String = "",
        @SerializedName("cmc")
        val cmc: Int = 0,
        @SerializedName("arena_id")
        val arenaId: Int = 0,
        @SerializedName("rarity")
        val rarity: String = "",
        @SerializedName("frame")
        val frame: String = "",
        @SerializedName("oracle_id")
        val oracleId: String = "",
        @SerializedName("artist")
        val artist: String = "",
        @SerializedName("released_at")
        val releasedAt: String = "",
        @SerializedName("colors")
        val colors: List<String>?,
        @SerializedName("illustration_id")
        val illustrationId: String = "",
        @SerializedName("promo")
        val promo: Boolean = false,
        @SerializedName("mtgo_id")
        val mtgoId: Int = 0,
        @SerializedName("rulings_uri")
        val rulingsUri: String = "",
        @SerializedName("collector_number")
        val collectorNumber: String = "",
        @SerializedName("set_uri")
        val setUri: String = "",
        @SerializedName("lang")
        val lang: String = "",
        @SerializedName("highres_image")
        val highresImage: Boolean = false,
        @SerializedName("digital")
        val digital: Boolean = false,
        @SerializedName("set")
        val set: String = "",
        @SerializedName("reprint")
        val reprint: Boolean = false,
        @SerializedName("full_art")
        val fullArt: Boolean = false,
        @SerializedName("image_uris")
        val imageUris: ImageUris,
        @SerializedName("uri")
        val uri: String = "",
        @SerializedName("layout")
        val layout: String = "",
        @SerializedName("multiverse_ids")
        val multiverseIds: List<Int>?,
        @SerializedName("oversized")
        val oversized: Boolean = false,
        @SerializedName("story_spotlight")
        val storySpotlight: Boolean = false,
        @SerializedName("card_faces")
        val cardFaces: List<CardFace>?,
        @SerializedName("flavor_text")
        val flavorText: String? = "") {

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