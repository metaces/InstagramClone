package com.example.instagramclone.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.instagramclone.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostRepository(
    private val apiService: ApiService,
    private val postDao: PostDao
) {

    fun getPosts(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                postDao.getPosts()
            }
        ).flow.map { pagingData ->
            pagingData.map { post ->
                post
            }
        }
    }

    suspend fun refreshPosts() {
        val remotePosts = apiService.getPosts()
        postDao.insertPost(remotePosts)
    }

}