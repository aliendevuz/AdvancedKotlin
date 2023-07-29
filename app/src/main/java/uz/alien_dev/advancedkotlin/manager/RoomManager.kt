package uz.alien_dev.advancedkotlin.manager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.alien_dev.advancedkotlin.database.UserDao
import uz.alien_dev.advancedkotlin.model.User


@Database(
    entities = [User::class],
    version = 1
)
abstract class RoomManager : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: RoomManager? = null

        fun getDatabase(context: Context): RoomManager {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomManager::class.java,
                        "app_db"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()   // make comment to switch off UI threading
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
