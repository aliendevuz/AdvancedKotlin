package uz.alien_dev.advancedkotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.alien_dev.advancedkotlin.Advanced.Companion.localeManager
import uz.alien_dev.advancedkotlin.databinding.ActivityMainBinding

class ActivityMain : AppCompatActivity() {

    private val context = this
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localeManager!!.setLocale(context)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun onResume() {
        super.onResume()
        localeManager!!.setLocale(context)
    }

    private fun init() {
        val buttonOpen = binding.buttonOpen
        buttonOpen.setOnClickListener { openActivity(ActivityPreference::class.java) }
    }

    private fun openActivity(activity: Class<*>) {
        val intent = Intent(context, activity)
        startActivity(intent)
    }
}