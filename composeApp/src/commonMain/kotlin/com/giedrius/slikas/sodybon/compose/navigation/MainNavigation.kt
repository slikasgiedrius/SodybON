package com.giedrius.slikas.sodybon.compose.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.giedrius.slikas.sodybon.compose.navigation.MainNavigationDirections.Actions.navigateBack
import com.giedrius.slikas.sodybon.compose.navigation.MainNavigationDirections.Actions.openArticle
import com.giedrius.slikas.sodybon.compose.navigation.Navigations.MAIN
import com.giedrius.slikas.sodybon.screens.detailed.DetailedArticle
import com.giedrius.slikas.sodybon.screens.home.HomeScreen
import com.giedrius.slikas.sodybon.screens.profile.ProfileScreen

internal object Navigations {
    const val MAIN = "Main"
}

@Composable
fun MainNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MAIN
    ) {
        navigation(startDestination = MainNavigationDirections.Routes.HOME, route = MAIN) {
            composable(
                route = MainNavigationDirections.Routes.HOME
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HomeScreen(
                        onPropertyClicked = {
                            navController.openArticle(articleTitle = it)
                        }
                    )
                }
            }
            composable(
                route = MainNavigationDirections.Routes.ARTICLE,
                arguments = listOf(
                    navArgument(name = MainNavigationDirections.ArticleArgs.TITLE) {
                        type = NavType.StringType
                    }
                )
            ) {
                DetailedArticle(
                    title = it.arguments?.getString(MainNavigationDirections.ArticleArgs.TITLE),
                    onNavigateBack = {
                        navController.navigateBack()
                    }
                )
            }
            composable(MainNavigationDirections.Routes.PROFILE) {
                ProfileScreen()
            }
        }
    }
}