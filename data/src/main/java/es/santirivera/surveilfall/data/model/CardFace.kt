package es.santirivera.surveilfall.data.model

import com.google.gson.annotations.SerializedName

data class CardFace(@SerializedName("oracle_text")
                    val oracleText: String = "",
                    @SerializedName("artist")
                    val artist: String = "",
                    @SerializedName("mana_cost")
                    val manaCost: String = "",
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("type_line")
                    val typeLine: String = "",
                    @SerializedName("image_uris")
                    val imageUris: ImageUris,
                    @SerializedName("power")
                    val power: String = "",
                    @SerializedName("toughness")
                    val toughness: String = "",
                    @SerializedName("colors")
                    val colors: List<String>?,
                    @SerializedName("illustration_id")
                    val illustrationId: String = "")