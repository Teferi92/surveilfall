package es.santirivera.surveilfall.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Legalities(
        @SerializedName("standard")
        var standard: String = "",
        @SerializedName("frontier")
        var frontier: String = "",
        @SerializedName("legacy")
        var legacy: String = "",
        @SerializedName("commander")
        var commander: String = "",
        @SerializedName("modern")
        var modern: String = "",
        @SerializedName("pauper")
        var pauper: String = "",
        @SerializedName("future")
        var future: String = "",
        @SerializedName("vintage")
        var vintage: String = "",
        @SerializedName("duel")
        var duelCommander: String = "",
        @SerializedName("1v1")
        var oneVsOneCommander: String = "",
        @SerializedName("brawl")
        var brawl: String = "",
        @SerializedName("penny")
        var penny: String = "",
        @SerializedName("oldschool")
        var oldSchool: String = "") : RealmObject() {

    private val formatArray: Array<String>
        get() = arrayOf(
                "Standard",
                "Future Standard",
                "Modern",
                "Legacy",
                "Vintage",
                "Pauper",
                "Frontier",
                "EDH",
                "Duel Commander",
                "Penny",
                "Old School")

    private val legalityArray: Array<String>
        get() = arrayOf(
                standard,
                future,
                modern,
                legacy,
                vintage,
                pauper,
                frontier,
                commander,
                duelCommander,
                penny,
                oldSchool
        )

    fun toLegalityList(): ArrayList<Legality> {
        val list = ArrayList<Legality>()
        val formatArray = this.formatArray
        val legalityArray = this.legalityArray

        for ((i, name) in formatArray.withIndex()) {
            val value = legalityArray[i]
            if (value.isValid()) {
                list.add(toLegality(name, value))
            }
        }
        return list
    }

    private fun toLegality(name: String, status: String): Legality {
        return when (status) {
            "legal" -> Legality(name, Legality.Status.LEGAL)
            "not_legal" -> Legality(name, Legality.Status.NOT_LEGAL)
            "banned" -> Legality(name, Legality.Status.BANNED)
            "restricted" -> Legality(name, Legality.Status.RESTRICTED)
            else -> throw RuntimeException("Illegal argument (Name: $name, Status: $status)")
        }
    }

}

private fun String.isValid(): Boolean {
    return equals("legal") || equals("not_legal") || equals("banned") || equals("restricted")
}
