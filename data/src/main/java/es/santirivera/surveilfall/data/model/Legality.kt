package es.santirivera.surveilfall.data.model

class Legality(val format: String, val status: Status) {
    enum class Status(val description: String, val legality: String) {
        LEGAL("L", "legal"),
        NOT_LEGAL("NL", "not_legal"),
        BANNED("B", "banned"),
        RESTRICTED("R", "restricted")
    }
}
