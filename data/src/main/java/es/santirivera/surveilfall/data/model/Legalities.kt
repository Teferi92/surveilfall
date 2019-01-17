package es.santirivera.surveilfall.data.model

import com.google.gson.annotations.SerializedName

data class Legalities(
        @SerializedName("standard")
        val standard: String = "",
        @SerializedName("frontier")
        val frontier: String = "",
        @SerializedName("legacy")
        val legacy: String = "",
        @SerializedName("commander")
        val commander: String = "",
        @SerializedName("modern")
        val modern: String = "",
        @SerializedName("pauper")
        val pauper: String = "",
        @SerializedName("future")
        val future: String = "",
        @SerializedName("vintage")
        val vintage: String = "",
        @SerializedName("duel")
        val duelCommander: String = "",
        @SerializedName("1v1")
        val oneVsOneCommander: String = "",
        @SerializedName("brawl")
        val brawl: String = "",
        @SerializedName("penny")
        val penny: String = "") {


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