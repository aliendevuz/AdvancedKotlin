package uz.alien_dev.advancedkotlin.database

import android.app.Application
import uz.alien_dev.advancedkotlin.manager.RoomManager
import uz.alien_dev.advancedkotlin.model.User

class UserRepository(application: Application) {

    private var userDao = RoomManager.getDatabase(application).userDao()

    fun saveUser(user: User) {
        userDao.saveUser(user)
    }

    fun loadUser(): List<User> {
        return userDao.loadUsers()
    }
}
