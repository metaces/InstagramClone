package com.example.instagramclone.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.instagramclone.model.Post

@Composable
fun PostDetailScreen(post: Post) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = rememberImagePainter(post.imageUrl),
            contentDescription = "Post Image",
            modifier = Modifier.fillMaxWidth()
                .height(250.dp)
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text(
            text = post.userName, fontWeight = FontWeight.Bold
        )
        Text(
            text = post.caption
        )
        Text(
            text = "${post.likes} likes", fontWeight = FontWeight.Bold
        )
    }
}