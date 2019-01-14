package es.santirivera.surveilfall.data.model

import com.google.gson.annotations.SerializedName

data class ImageUris(
        @SerializedName("small")
        val small: String = "",
        @SerializedName("normal")
        val normal: String = "",
        @SerializedName("large")
        val large: String = "",
        @SerializedName("png")
        val png: String = "",
        @SerializedName("border_crop")
        val borderCrop: String = "",
        @SerializedName("art_crop")
        val artCrop: String = "")