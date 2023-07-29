package uz.alien_dev.advancedkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
open class User(@PrimaryKey val id: Int, @ColumnInfo(name = "full_name") val full_name: String)
