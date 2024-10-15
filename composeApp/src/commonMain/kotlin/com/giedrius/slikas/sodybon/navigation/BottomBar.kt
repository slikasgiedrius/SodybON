package com.giedrius.slikas.sodybon.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.screens.feature.login.LoginViewModel
import com.giedrius.slikas.sodybon.utils.Navigation.logBottomNavigationItemClicked
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.koinInject

private val BOTTOM_NAV_ICON_SIZE = 30.dp
private val CUSTOM_BOTTOM_BAR_HEIGHT = 88.dp

enum class BottomBarTabs {
    Home,
    Profile,
}

@Composable
fun BottomBar(
    loginViewModel: LoginViewModel = koinInject(),
) {
    val uiState by loginViewModel.uiState.collectAsState()

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var selectedScreen by remember { mutableStateOf(BottomBarTabs.Home) }

    Scaffold(
        bottomBar = {
            if (currentRoute in BottomBarTabs.entries.map { it.name }) {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.height(CUSTOM_BOTTOM_BAR_HEIGHT)
                ) {
                    // Home tab
                    NavigationBarItem(
                        icon = {
                            Icon(
                                modifier = Modifier.size(BOTTOM_NAV_ICON_SIZE),
                                imageVector = Icons.Default.Home,
                                contentDescription = BottomBarTabs.Home.name
                            )
                        },
                        label = {
                            BottomBarItemText(
                                text = BottomBarTabs.Home.name
                            )
                        },
                        selected = BottomBarTabs.Home == selectedScreen,
                        onClick = {
                            Logger.logBottomNavigationItemClicked(BottomBarTabs.Home.name)
                            selectedScreen = BottomBarTabs.Home
                            navigateToTab(
                                navController = navController,
                                tab = BottomBarTabs.Home
                            )
                        },
                    )

                    // Profile tab
                    NavigationBarItem(
                        icon = {
                            if (uiState.currentProfile?.photoUrl != null) {
                                KamelImage(
                                    modifier = Modifier
                                        .size(BOTTOM_NAV_ICON_SIZE)
                                        .clip(CircleShape),
                                    resource = asyncPainterResource(uiState.currentProfile?.photoUrl!!),
                                    contentDescription = "",
                                    contentScale = ContentScale.Fit,
                                    onLoading = { CircularProgressIndicator(it) },
                                    onFailure = {
                                        Column {
                                            Text(
                                                text = "Failed to load",
                                                fontWeight = FontWeight.Bold,
                                            )
                                        }
                                    })
                            } else {
                                Icon(
                                    modifier = Modifier.size(BOTTOM_NAV_ICON_SIZE),
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = uiState.currentProfile?.firstName
                                        ?: BottomBarTabs.Profile.name,
                                )
                            }
                        },
                        label = {
                            BottomBarItemText(
                                text = uiState.currentProfile?.firstName
                                    ?: BottomBarTabs.Profile.name
                            )
                        },
                        selected = BottomBarTabs.Profile == selectedScreen,
                        onClick = {
                            Logger.logBottomNavigationItemClicked(BottomBarTabs.Profile.name)
                            selectedScreen = BottomBarTabs.Profile
                            navigateToTab(
                                navController = navController,
                                tab = BottomBarTabs.Profile
                            )
                        },
                    )
                }
            }
        }
    ) { innerPadding ->
        MainNavigation(
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            navController = navController,
        )
    }
}

@Composable
fun BottomBarItemText(
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    Text(
        text = text,
        color = textColor
    )
}