package es.santirivera.surveilfall.base.app

import android.app.Application

import es.santirivera.surveilfall.di.components.ApplicationComponent
import es.santirivera.surveilfall.di.components.DaggerApplicationComponent
import es.santirivera.surveilfall.di.modules.ApplicationModule
import io.realm.Realm
import io.realm.RealmConfiguration



class AndroidApplication : Application() {

    private var component: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("myRealmFile")
                .build()
        Realm.setDefaultConfiguration(realmConfig)

        initDependencies()
    }

    private fun initDependencies() {
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        component!!.inject(this)
    }

    fun component(): ApplicationComponent? {
        return component
    }
}
