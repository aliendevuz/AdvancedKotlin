package uz.alien_dev.advancedkotlin

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import uz.alien_dev.advancedkotlin.manager.LocaleManager

class Advanced: Application() {

    private val context = this

    companion object {
        var localeManager: LocaleManager? = null
    }

    override fun onCreate() {
        super.onCreate()
        localeManager = LocaleManager(context)
        localeManager!!.setLocale(context)
        Realm.init(context)
        val config = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }
}
