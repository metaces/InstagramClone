package com.example.instagramclone.data

import com.example.instagramclone.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("/posts")
    suspend fun getPosts(): List<Post>
}