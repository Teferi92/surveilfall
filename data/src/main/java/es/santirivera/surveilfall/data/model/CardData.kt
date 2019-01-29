package es.santirivera.surveilfall.data.model

import io.realm.RealmObject

open class CardData(var name: String? = null,
               var typeLine: String? = null,
               var oracleText: String? = null,
               var loyalty: String? = null,
               var power: String? = null,
               var toughness: String? = null,
               var manaCost: String? = null,
               var flavorText: String? = null,
               var artist: String? = null,
               var imageUris: ImageUris? = null) : RealmObject()
