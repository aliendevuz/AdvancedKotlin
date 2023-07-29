package uz.alien_dev.advancedkotlin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.alien_dev.advancedkotlin.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: User)

    @Query("SELECT * FROM user_table")
    fun loadUsers(): List<User>
}
