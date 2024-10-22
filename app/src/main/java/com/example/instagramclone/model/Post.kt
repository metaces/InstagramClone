package com.example.instagramclone.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey val id: String,
    val userId: String,
    val userName: String,
    val userAvatarUrl: String,
    val imageUrl: String,
    val caption: String,
    val likes: Int,
    @Embedded val comments: List<Comment>
)

data class Comment(
    val id: String,
    val userId: String,
    val userName: String,
    val commentText: String
)
