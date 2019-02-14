package es.santirivera.surveilfall.data.model

import com.google.gson.annotations.SerializedName

class Identifier(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("mtgo_id")
        var mtgoId: String? = null,
        @SerializedName("multiverse_id")
        var multiverseId: String? = null,
        @SerializedName("name")
        var name: String? = null)