package com.example.instagramclone.data

import android.content.Context
import androidx.room.Room

object DatabaseModule {
    fun provideDatabase(context: Context): PostDatabase {
        return Room.databaseBuilder(
            context,
            PostDatabase::class.java,
            "post_database"
        ).build()
    }

    fun providePostDao(database: PostDatabase): PostDao {
        return database.postDao()
    }

    fun providePostRepository(context: Context): PostRepository {
        val database = provideDatabase(context)
        return PostRepository(RetrofitInstance.api, database.postDao())
    }
}