package uz.alien_dev.advancedkotlin.manager

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import java.util.LinkedHashSet
import java.util.Locale

class LocaleManager(context: Context) {

    private val preferences = PreferencesManager.getInstance(context)

    companion object {
        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_RUSSIAN = "ru"
        const val LANGUAGE_UZBEK = "uz"
        private const val LANGUAGE_KEY = "language"

        fun getLocale(res: Resources): Locale {
            val config = res.configuration
            return if (isAtLeastVersion(Build.VERSION_CODES.N)) config.locales[0] else config.locale
        }

        fun isAtLeastVersion(version: Int): Boolean {
            return Build.VERSION.SDK_INT >= version
        }
    }

    private val language = if (preferences.loadData(LANGUAGE_KEY) != "null") preferences.loadData(LANGUAGE_KEY) else LANGUAGE_UZBEK

    fun setLocale(context: Context): Context {
        return update(context, language)
    }

    private fun update(context: Context, language: String?): Context {
        updateResources(context, language)
        val appContext = context.applicationContext
        if (context != appContext)
            updateResources(appContext, language)
        return appContext
    }

    fun setNewLocale(context: Context, language: String) {
        persistLanguage(language)
        update(context, language)
    }

    private fun persistLanguage(language: String) {
        preferences.saveData(LANGUAGE_KEY, language)
    }

    private fun updateResources(context: Context, language: String?) {
        val locale = Locale(language!!)
        Locale.setDefault(locale)
        val res = context.resources
        val config = Configuration(res.configuration)
        if (isAtLeastVersion(Build.VERSION_CODES.N))
            setLocaleForApi24(config, locale)
        else if (isAtLeastVersion(Build.VERSION_CODES.JELLY_BEAN_MR1))
            config.setLocale(locale)
        else
            config.locale = locale
        res.updateConfiguration(config, res.displayMetrics)
    }

    private fun setLocaleForApi24(config: Configuration, locale: Locale) {
        val set = LinkedHashSet<Locale>()
        set.add(locale)
        val all = LocaleList.getDefault()
        for (i in 0 until all.size()) set.add(all[i])
        val locales = set.toTypedArray()
        config.setLocales(LocaleList(*locales))
    }
}