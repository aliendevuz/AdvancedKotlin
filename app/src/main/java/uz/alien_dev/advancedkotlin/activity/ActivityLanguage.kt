package uz.alien_dev.advancedkotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.alien_dev.advancedkotlin.Advanced
import uz.alien_dev.advancedkotlin.databinding.ActivityLanguageBinding
import uz.alien_dev.advancedkotlin.manager.LocaleManager

class ActivityLanguage : AppCompatActivity() {

    private val context = this
    private lateinit var binding: ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        val buttonEnglish = binding.buttonEnglish
        val buttonRussian = binding.buttonRussian
        val buttonUzbek = binding.buttonUzbek

        buttonEnglish.setOnClickListener {
            Advanced.localeManager!!.setNewLocale(context, LocaleManager.LANGUAGE_ENGLISH)
            finish()
        }

        buttonRussian.setOnClickListener {
            Advanced.localeManager!!.setNewLocale(context, LocaleManager.LANGUAGE_RUSSIAN)
            finish()
        }

        buttonUzbek.setOnClickListener {
            Advanced.localeManager!!.setNewLocale(context, LocaleManager.LANGUAGE_UZBEK)
            finish()
        }
    }
}