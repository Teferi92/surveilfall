package es.santirivera.surveilfall.data.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class WordBankItem(@PrimaryKey var name: String = "") : RealmModel {

    override fun toString(): String {
        return name.substring(0, 1).toUpperCase() + name.substring(1)
    }
}
