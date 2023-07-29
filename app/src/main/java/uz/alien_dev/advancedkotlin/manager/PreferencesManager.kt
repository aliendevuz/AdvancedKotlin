package uz.alien_dev.advancedkotlin.manager

import android.content.Context

class PreferencesManager(context: Context) {

    private val preferences = context.getSharedPreferences("My Preferences", Context.MODE_PRIVATE)

    companion object {
        private var preferencesManager: PreferencesManager? = null
        fun getInstance(context: Context): PreferencesManager {
            if (preferencesManager == null)
                preferencesManager = PreferencesManager(context)
            return preferencesManager!!
        }
    }

    fun saveData(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun loadData(key: String): String {
        return preferences.getString(key, "null").toString()
    }
}