package com.example.instagramclone.data

import com.example.instagramclone.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class PostRepository(
    private val api: PostApi,
    private val postDao: PostDao
) {

    fun getPosts(): Flow<List<Post>> = flow {
        try {
            val response = api.getPosts()
            postDao.clearPosts()
            postDao.insertPost(response)
            emit(response)
        } catch (e: Exception) {
            emit(postDao.getPosts().firstOrNull() ?: emptyList<Post>())
        }
    }.onStart {
        emit(postDao.getPosts().firstOrNull() ?: emptyList<Post>())
    }
}