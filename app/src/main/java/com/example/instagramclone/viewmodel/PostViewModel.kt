package com.example.instagramclone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.instagramclone.data.PostRepository
import com.example.instagramclone.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(
    private val repository: PostRepository
) : ViewModel() {

    val pagedPosts: Flow<PagingData<Post>> = repository.getPosts().cachedIn(viewModelScope)

    fun refreshPosts() {
        viewModelScope.launch {
            repository.refreshPosts()
        }
    }

//    private val _postState = MutableStateFlow<List<Post>>(emptyList())
//    val postState: StateFlow<List<Post>> = _postState.asStateFlow()
//
//    private val _loadingState = MutableStateFlow<Boolean>(false)
//    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow()
//
//    private val _errorState = MutableStateFlow<String?>(null)
//    val errorState: StateFlow<String?> = _errorState.asStateFlow()
//
//    init {
//        fetchPosts()
//    }
//
//    fun fetchPosts() {
//        viewModelScope.launch {
//            _loadingState.value = true
//            try {
//                repository.getPosts().collect { posts ->
//                    _postState.value = posts
//                   _errorState.value = null
//                }
//            } catch (e: Exception) {
//                _errorState.value = "Failed to fetch posts"
//            } finally {
//                _loadingState.value = false
//            }
//        }
//    }
}