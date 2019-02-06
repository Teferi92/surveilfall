package es.santirivera.surveilfall.data.model

data class CardData(val name: String? = null,
                    val typeLine: String? = null,
                    val oracleText: String? = null,
                    val loyalty: String? = null,
                    val power: String? = null,
                    val toughness: String? = null,
                    val manaCost: String? = null,
                    val flavorText: String? = null,
                    val artist: String? = null,
                    val imageUris: ImageUris? = null)
