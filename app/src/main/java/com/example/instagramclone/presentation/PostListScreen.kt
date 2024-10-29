package com.example.instagramclone.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.example.instagramclone.model.Post
import com.example.instagramclone.viewmodel.PostViewModel

@Composable
fun PostListScreen(viewModel: PostViewModel, navController: NavHostController) {
//    val posts = viewModel.postState.collectAsState().value
//    val loading = viewModel.loadingState.collectAsState().value
//    val error = viewModel.errorState.collectAsState().value

    val pagedPosts = viewModel.pagedPosts.collectAsLazyPagingItems()

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pagedPosts.itemCount) {
            PostItem(post = pagedPosts[it]!!) {
                navController.navigate(Screen.PostDetail.createRoute(pagedPosts[it]?.id?.toInt()!!))
            }
        }

        pagedPosts.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    item {
                        Text(
                            text = "Error loading more posts",
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun PostItem(post: Post, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(post.userAvatarUrl),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = post.userName, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.size(8.dp))
        Image(
            painter = rememberImagePainter(post.imageUrl),
            contentDescription = "Post Image",
            modifier = Modifier
                .fillMaxSize()
                .height(400.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "${post.likes} likes", fontWeight = FontWeight.Bold)
        Text(text = post.caption)
    }
}