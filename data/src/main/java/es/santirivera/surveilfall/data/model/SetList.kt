package es.santirivera.surveilfall.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class SetList(@SerializedName("data") var data: List<Set>? = null)