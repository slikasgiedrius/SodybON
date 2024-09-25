package com.giedrius.slikas.sodybon.compose.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

const val BOTTOM_INSET_HEIGHT = 32
private const val DEFAULT_BOTTOM_BAR_HEIGHT = 56
const val CUSTOM_BOTTOM_BAR_HEIGHT = DEFAULT_BOTTOM_BAR_HEIGHT + BOTTOM_INSET_HEIGHT

internal enum class BottomBarTabs {
    Home,
    Profile,
}

@Composable
fun BottomBar() {
    val navController = rememberNavController()
    var selectedScreen by remember { mutableStateOf(BottomBarTabs.Home) }

    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.height(CUSTOM_BOTTOM_BAR_HEIGHT.dp),
            ) {
                //Home tab
                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = BottomBarTabs.Home.name
                        )
                    },
                    label = { Text(BottomBarTabs.Home.name) },
                    selected = BottomBarTabs.Home == selectedScreen,
                    onClick = {
                        selectedScreen = BottomBarTabs.Home
                        navController.navigate(
                            route = BottomBarTabs.Home.name
                        ) {
                            navController.graph.startDestinationRoute?.let {
                                popUpTo(it) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    modifier = Modifier.padding(0.dp)
                )

                //Profile tab
                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = BottomBarTabs.Profile.name
                        )
                    },
                    label = { Text(BottomBarTabs.Profile.name) },
                    selected = BottomBarTabs.Profile == selectedScreen,
                    onClick = {
                        selectedScreen = BottomBarTabs.Profile
                        navController.navigate(
                            route = BottomBarTabs.Profile.name
                        ) {
                            navController.graph.startDestinationRoute?.let {
                                popUpTo(it) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    modifier = Modifier.padding(0.dp)
                )
            }
        },
        content = {
            MainNavigation(
                navController = navController
            )
        }
    )
}