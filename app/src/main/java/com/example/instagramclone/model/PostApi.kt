package com.example.instagramclone.model

data class Post(
    val id: String,
    val userId: String,
    val userName: String,
    val userAvatarUrl: String,
    val imageUrl: String,
    val caption: String,
    val likes: Int,
    val comments: List<Comment>
)

data class Comment(
    val id: String,
    val userId: String,
    val userName: String,
    val commentText: String,
)
