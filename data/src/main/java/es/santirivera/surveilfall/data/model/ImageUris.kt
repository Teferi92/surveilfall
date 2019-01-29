package es.santirivera.surveilfall.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class ImageUris(
        @SerializedName("small")
        var small: String = "",
        @SerializedName("normal")
        var normal: String = "",
        @SerializedName("large")
        var large: String = "",
        @SerializedName("png")
        var png: String = "",
        @SerializedName("border_crop")
        var borderCrop: String = "",
        @SerializedName("art_crop")
        var artCrop: String = "") : RealmObject()