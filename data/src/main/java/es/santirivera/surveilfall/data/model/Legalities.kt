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


    fun toLegalityList(): ArrayList<Legality> {
        val list = ArrayList<Legality>()
        list.add(toLegality("Standard", standard))
        list.add(toLegality("Future Standard", future))
        list.add(toLegality("Modern", modern))
        list.add(toLegality("Legacy", legacy))
        list.add(toLegality("Vintage", vintage))
        list.add(toLegality("Pauper", pauper))
        list.add(toLegality("Frontier", frontier))
        list.add(toLegality("EDH", commander))
        list.add(toLegality("Duel Commander", duelCommander))
        list.add(toLegality("1v1 Commander.", oneVsOneCommander))
        list.add(toLegality("Penny", penny))
        list.add(toLegality("Brawl", brawl))
        list.add(toLegality("Old School", oldSchool))
        return list
    }

    private fun toLegality(name: String, status: String): Legality {
        return when (status) {
            "legal" -> Legality(name, Legality.Status.LEGAL)
            "not_legal" -> Legality(name, Legality.Status.NOT_LEGAL)
            "banned" -> Legality(name, Legality.Status.BANNED)
            "restricted" -> Legality(name, Legality.Status.RESTRICTED)
            else -> throw RuntimeException("Illegal argument")
        }
    }

}