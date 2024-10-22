package com.example.instagramclone.data

import com.example.instagramclone.model.Post
import retrofit2.http.GET

interface PostApi {

    @GET("/posts")
    suspend fun getPosts(): List<Post>
}