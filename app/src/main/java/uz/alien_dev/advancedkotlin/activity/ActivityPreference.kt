package uz.alien_dev.advancedkotlin.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.alien_dev.advancedkotlin.databinding.ActivityPreferenceBinding
import uz.alien_dev.advancedkotlin.manager.PreferencesManager

class ActivityPreference : AppCompatActivity() {

    private val context = this
    private lateinit var binding: ActivityPreferenceBinding
//    private val preferencesManager = PreferencesManager(context, "My Preferences", MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        val editTextEmail = binding.editTextEmail
        val buttonSave = binding.buttonSave
        val buttonLoad = binding.buttonLoad
        val textViewEmail = binding.textViewEmail

        buttonSave.setOnClickListener {
            val stringEmail = editTextEmail.text.toString().trim()
            PreferencesManager.getInstance(context).saveData("email", stringEmail)
        }

        buttonLoad.setOnClickListener {
            textViewEmail.text = PreferencesManager.getInstance(context).loadData("email")
        }
    }
}