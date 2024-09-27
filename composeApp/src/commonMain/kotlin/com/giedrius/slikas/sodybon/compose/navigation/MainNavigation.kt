package com.giedrius.slikas.sodybon.compose.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.giedrius.slikas.sodybon.compose.base.BOTTOM_INSET_HEIGHT
import com.giedrius.slikas.sodybon.compose.base.CUSTOM_BOTTOM_BAR_HEIGHT
import com.giedrius.slikas.sodybon.compose.base.TOP_INSET_HEIGHT
import com.giedrius.slikas.sodybon.compose.navigation.MainNavigationDirections.Actions.navigateBack
import com.giedrius.slikas.sodybon.compose.navigation.MainNavigationDirections.Actions.openDetailedProperty
import com.giedrius.slikas.sodybon.compose.navigation.Navigations.MAIN
import com.giedrius.slikas.sodybon.screens.detailed_property.DetailedArticle
import com.giedrius.slikas.sodybon.screens.home.HomeScreen
import com.giedrius.slikas.sodybon.screens.profile.ProfileScreen

internal object Navigations {
    const val MAIN = "Main"
}

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
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
                    modifier = Modifier.fillMaxSize(),
                    propertyId = it.arguments?.getString(MainNavigationDirections.DetailedPropertyArgs.PROPERTY_ID),
                    onNavigateBack = {
                        navController.navigateBack()
                    }
                )
            }
            composable(MainNavigationDirections.Routes.PROFILE) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                }
                ProfileScreen()
            }
        }
    }
}