package com.example.instagramclone.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.instagramclone.model.Post
import kotlinx.coroutines.flow.Flow

interface PostDao {

    @Query("SELECT * FROM posts")
    fun getPosts(): Flow<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(posts: List<Post>)

    @Query("DELETE FROM posts")
    suspend fun clearPosts()

}