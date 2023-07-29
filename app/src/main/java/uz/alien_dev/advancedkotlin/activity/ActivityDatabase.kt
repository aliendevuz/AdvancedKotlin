package uz.alien_dev.advancedkotlin.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import uz.alien_dev.advancedkotlin.database.UserRepository
import uz.alien_dev.advancedkotlin.databinding.ActivityDatabaseBinding
import uz.alien_dev.advancedkotlin.manager.RealmManager
import uz.alien_dev.advancedkotlin.model.Post
import uz.alien_dev.advancedkotlin.model.User
import java.lang.Exception
import java.util.concurrent.Executors

class ActivityDatabase : AppCompatActivity() {

    private lateinit var binding: ActivityDatabaseBinding

    private lateinit var textViewDatabase: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init views
        textViewDatabase = binding.textViewDatabase
        val buttonRealmDB = binding.buttonRealmDB
        val buttonRoomDB = binding.buttonRoomDB

        buttonRealmDB.setOnClickListener {
            realmDB()
        }

        buttonRoomDB.setOnClickListener {
            roomDB()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun realmDB() {
        val post = Post(1, "Academy")
        RealmManager.instance!!.savePost(post)
        val posts = RealmManager.instance!!.loadPosts()
        textViewDatabase.text = "Realm DB's size is ${posts.size}"
    }

    @SuppressLint("SetTextI18n")
    private fun roomDB() {
        val repository = UserRepository(application)
        val user = User(0, "Ibrohim")
        try {
            repository.saveUser(user)
            textViewDatabase.text = "Room DB's size is ${repository.loadUser().size} on UI"
        } catch (ignored: Exception) {
            userExecutor(repository, user)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun userExecutor(repository: UserRepository, user: User) {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        executor.execute {
            repository.saveUser(user)
            val users = repository.loadUser()
            handler.post {
                textViewDatabase.text = "Room DB's size is ${users.size}"
            }
        }
    }
}
