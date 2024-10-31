package com.giedrius.slikas.sodybon.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.giedrius.slikas.sodybon.navigation.MainNavigationDirections.Actions.navigateBack
import com.giedrius.slikas.sodybon.navigation.MainNavigationDirections.Actions.openDetailedProperty
import com.giedrius.slikas.sodybon.screens.feature.detailed_property.DetailedPropertyScreen
import com.giedrius.slikas.sodybon.screens.feature.home.HomeScreen
import com.giedrius.slikas.sodybon.screens.feature.profile.ProfileScreen

const val MAIN = "Main"

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = MAIN,
    ) {
        navigation(startDestination = MainNavigationDirections.Routes.HOME, route = MAIN) {
            composable(
                route = MainNavigationDirections.Routes.HOME
            ) {
                HomeScreen(
                    modifier = Modifier.padding(bottom = CUSTOM_BOTTOM_BAR_HEIGHT),
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
                DetailedPropertyScreen(
                    propertyId = it.arguments?.getString(MainNavigationDirections.DetailedPropertyArgs.PROPERTY_ID),
                    onNavigateBack = {
                        navController.navigateBack()
                    },
                )
            }
            composable(MainNavigationDirections.Routes.PROFILE) {
                ProfileScreen(
                    modifier = Modifier.padding(bottom = CUSTOM_BOTTOM_BAR_HEIGHT),
                )
            }
        }
    }
}