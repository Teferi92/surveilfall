package es.santirivera.surveilfall.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Set(
        @SerializedName("code")
        var code: String = "",
        @SerializedName("uri")
        var uri: String = "",
        @SerializedName("card_count")
        var cardCount: Int = 0,
        @SerializedName("name")
        var name: String = "",
        @SerializedName("id")
        var id: String = "",
        @SerializedName("parent_set_code")
        var parentSetCode: String = "",
        @SerializedName("icon_svg_uri")
        var iconUri: String = ""
) : Parcelable, RealmObject()