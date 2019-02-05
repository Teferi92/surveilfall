package es.santirivera.surveilfall.util.realm

import io.realm.DynamicRealm
import io.realm.FieldAttribute
import io.realm.RealmMigration
import io.realm.RealmSchema

class SurveilfallSchemaMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema
        if (!schema.contains("WordBankItem")) {
            schema.create("WordBankItem")
                    .addField("name", String::class.java)
        }
    }
}