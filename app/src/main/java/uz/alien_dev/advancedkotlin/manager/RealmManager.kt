package uz.alien_dev.advancedkotlin.manager

import io.realm.Realm
import uz.alien_dev.advancedkotlin.model.Post

class RealmManager {

    companion object {
        private var realmManager: RealmManager? = null
        private lateinit var realm: Realm

        val instance: RealmManager?
            get() {
                if (realmManager == null) realmManager = RealmManager()
                return realmManager
            }
    }

    init {
        realm = Realm.getDefaultInstance()
    }

    fun savePost(post: Post) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(post)
        realm.commitTransaction()
    }

    fun loadPosts(): ArrayList<Post> {
        val posts = ArrayList<Post>()
        val results = realm.where(Post::class.java).findAll()
        for (post in results) posts.add(post)
        return posts
    }
}