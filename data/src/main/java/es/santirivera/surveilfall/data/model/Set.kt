package es.santirivera.surveilfall.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Set(
        @SerializedName("code")
        val code: String = "",
        @SerializedName("uri")
        val uri: String = "",
        @SerializedName("card_count")
        val cardCount: Int = 0,
        @SerializedName("name")
        val name: String = "",
        @SerializedName("id")
        val id: String = "",
        @SerializedName("parent_set_code")
        val parentSetCode: String = "",
        @SerializedName("icon_svg_uri")
        val iconUri: String = ""
) : Parcelable