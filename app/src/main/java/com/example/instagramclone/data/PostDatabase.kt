package com.example.instagramclone.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.instagramclone.model.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class PostDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
}