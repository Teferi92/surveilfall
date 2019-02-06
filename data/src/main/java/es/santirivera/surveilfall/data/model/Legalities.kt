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
        val penny: String = "",
        @SerializedName("oldschool")
        val oldSchool: String = "") {

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
