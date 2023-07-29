package uz.alien_dev.advancedkotlin.activity

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import uz.alien_dev.advancedkotlin.databinding.ActivityStorageBinding
import uz.alien_dev.advancedkotlin.utils.Utils.fireToast
import java.io.File
import java.io.IOException

class ActivityStorage : AppCompatActivity() {

    private val context = this
    private val isPersistent = true
    private lateinit var binding: ActivityStorageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init views
        val bInternalSave = binding.bInternalSave
        val bInternalRead = binding.bInternalRead
        val bInternalDelete = binding.bInternalDelete
        val bExternalSave = binding.bExternalSave
        val bExternalRead = binding.bExternalRead
        val bExternalDelete = binding.bExternalDelete
        val bTakePhoto = binding.bTakePhoto

        bInternalSave.setOnClickListener { checkStoragePaths() }
        bInternalRead.setOnClickListener { createInternalFile() }
        bInternalDelete.setOnClickListener {  }
        bExternalSave.setOnClickListener {  }
        bExternalRead.setOnClickListener {  }
        bExternalDelete.setOnClickListener {  }
        bTakePhoto.setOnClickListener {  }
//
//        checkStoragePaths()
//        createInternalFile()
    }

    private fun saveInternalFile() {}
    private fun readInternalFile() {}

    private fun saveExternalFile() {}
    private fun readExternalFile() {}

    private fun checkStoragePaths() {
        val internalM1 = getDir("custom", 0)
        val internalM2 = filesDir

        val externalM1 = getExternalFilesDir(null)
        val externalM2 = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val externalM3 = externalCacheDir

        Log.d("@@@@", internalM1.absolutePath)
        Log.d("@@@@", internalM2.absolutePath)

        Log.d("@@@@", externalM1!!.absolutePath)
        Log.d("@@@@", externalM2!!.absolutePath)
        Log.d("@@@@", externalM3!!.absolutePath)
    }

    private fun createInternalFile() {
        val fileName = "pdp_internal.txt"
        val file = if (isPersistent) File(filesDir, fileName) else File(cacheDir, fileName)
        if (!file.exists()) {
            try {
                file.createNewFile()
                fireToast(context, String.format("File %s has been created", fileName))
            } catch (e: IOException) {
                fireToast(context, String.format("File %s creation failed", fileName))
            }
        } else {
            fireToast(context, String.format("File %s already exist", fileName))
        }
    }
}