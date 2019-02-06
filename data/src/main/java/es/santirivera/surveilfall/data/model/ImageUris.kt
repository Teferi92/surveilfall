package es.santirivera.surveilfall.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class ImageUris(
        @SerializedName("small")
        @ColumnInfo(name = "small")
        val small: String = "",
        @SerializedName("normal")
        @ColumnInfo(name = "normal")
        val normal: String = "",
        @SerializedName("large")
        @ColumnInfo(name = "large")
        val large: String = "",
        @SerializedName("png")
        @ColumnInfo(name = "png")
        val png: String = "",
        @SerializedName("border_crop")
        @ColumnInfo(name = "border_crop")
        val borderCrop: String = "",
        @SerializedName("art_crop")
        @ColumnInfo(name = "art_crop")
        val artCrop: String = "")