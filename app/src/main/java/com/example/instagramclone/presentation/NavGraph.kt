package com.example.instagramclone.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instagramclone.viewmodel.PostViewModel

sealed class Screen(val route: String) {
    object PostList: Screen("post_list")
    object PostDetail: Screen("post_detail/{postId}") {
        fun createRoute(postId: Int) = "post_detail/$postId"
    }
}

@Composable
fun InstagramNavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: PostViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.PostList.route,
        modifier = modifier
    ) {
        composable(Screen.PostList.route) {
            PostListScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screen.PostDetail.route) { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")
            val post = viewModel.postState.collectAsState().value.find { it.id == postId }
            post?.let {
                PostDetailScreen(post = it)
            }
        }
    }
}