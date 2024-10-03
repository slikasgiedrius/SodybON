package com.giedrius.slikas.sodybon.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.giedrius.slikas.sodybon.navigation.MainNavigationDirections.Actions.navigateBack
import com.giedrius.slikas.sodybon.navigation.MainNavigationDirections.Actions.openDetailedProperty
import com.giedrius.slikas.sodybon.navigation.Navigations.MAIN
import com.giedrius.slikas.sodybon.screens.feature.detailed_property.DetailedArticle
import com.giedrius.slikas.sodybon.screens.feature.home.HomeScreen
import com.giedrius.slikas.sodybon.screens.feature.profile.ProfileScreen
import com.giedrius.slikas.sodybon.utils.extensions.topLevelFullScreenBackground

internal object Navigations {
    const val MAIN = "Main"
}

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = MAIN,
        modifier = modifier,
    ) {
        navigation(startDestination = MainNavigationDirections.Routes.HOME, route = MAIN) {
            composable(
                route = MainNavigationDirections.Routes.HOME
            ) {
                HomeScreen(
                    modifier = Modifier.topLevelFullScreenBackground(MaterialTheme.colors.background),
                    onPropertyClicked = {
                        navController.openDetailedProperty(propertyId = it)
                    }
                )
            }

            composable(
                route = MainNavigationDirections.Routes.DETAILED_PROPERTY,
                arguments = listOf(
                    navArgument(name = MainNavigationDirections.DetailedPropertyArgs.PROPERTY_ID) {
                        type = NavType.StringType
                    }
                )
            ) {
                DetailedArticle(
                    modifier = Modifier.topLevelFullScreenBackground(MaterialTheme.colors.error),
                    propertyId = it.arguments?.getString(MainNavigationDirections.DetailedPropertyArgs.PROPERTY_ID),
                    onNavigateBack = {
                        navController.navigateBack()
                    }
                )
            }
            composable(MainNavigationDirections.Routes.PROFILE) {
                ProfileScreen(
                    modifier = Modifier.topLevelFullScreenBackground(MaterialTheme.colors.secondary)
                )
            }
        }
    }
}