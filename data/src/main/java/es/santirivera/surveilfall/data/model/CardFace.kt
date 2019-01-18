package es.santirivera.surveilfall.data.model

import com.google.gson.annotations.SerializedName

data class CardFace(
        @SerializedName("artist")
        var artist: String? = "",
        @SerializedName("colorIndicator")
        var colorIndicator: List<String>?,
        @SerializedName("colors")
        var colors: List<String>?,
        @SerializedName("flavor_text")
        var flavorText: String? = "",
        @SerializedName("illustration_id")
        var illustrationId: String? = "",
        @SerializedName("image_uris")
        var imageUris: ImageUris,
        @SerializedName("loyalty")
        var loyalty: String? = "",
        @SerializedName("mana_cost")
        var manaCost: String? = "",
        @SerializedName("name")
        var name: String? = "",
        @SerializedName("oracle_text")
        var oracleText: String? = "",
        @SerializedName("power")
        var power: String? = "",
        @SerializedName("printed_name")
        var printedName: String? = "",
        @SerializedName("printed_text")
        var printedText: String? = "",
        @SerializedName("printed_type_line")
        var printedTypeLine: String? = "",
        @SerializedName("toughness")
        var toughness: String? = "",
        @SerializedName("type_line")
        var typeLine: String? = ""
)