package es.santirivera.surveilfall.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class CardFace(
        @SerializedName("artist")
        @ColumnInfo(name = "artist")
        var artist: String? = "",
        @SerializedName("colorIndicator")
        @ColumnInfo(name = "colorIndicator")
        val colorIndicator: ArrayList<String>? = null,
        @SerializedName("colors")
        @ColumnInfo(name = "colors")
        val colors: ArrayList<String>? = null,
        @SerializedName("flavor_text")
        @ColumnInfo(name = "flavor_text")
        val flavorText: String? = "",
        @SerializedName("illustration_id")
        @ColumnInfo(name = "illustration_id")
        val illustrationId: String? = "",
        @SerializedName("image_uris")
        @ColumnInfo(name = "image_uris")
        val imageUris: ImageUris? = null,
        @SerializedName("loyalty")
        @ColumnInfo(name = "loyalty")
        val loyalty: String? = "",
        @SerializedName("mana_cost")
        @ColumnInfo(name = "mana_cost")
        val manaCost: String? = "",
        @SerializedName("name")
        @ColumnInfo(name = "name")
        val name: String? = "",
        @SerializedName("oracle_text")
        @ColumnInfo(name = "oracle_text")
        val oracleText: String? = "",
        @SerializedName("power")
        @ColumnInfo(name = "power")
        val power: String? = "",
        @SerializedName("printed_name")
        @ColumnInfo(name = "printed_name")
        val printedName: String? = "",
        @SerializedName("printed_text")
        @ColumnInfo(name = "printed_text")
        val printedText: String? = "",
        @SerializedName("printed_type_line")
        @ColumnInfo(name = "printed_type_line")
        val printedTypeLine: String? = "",
        @SerializedName("toughness")
        @ColumnInfo(name = "toughness")
        val toughness: String? = "",
        @SerializedName("type_line")
        @ColumnInfo(name = "type_line")
        val typeLine: String? = ""
)