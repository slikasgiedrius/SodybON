package com.giedrius.slikas.sodybon.compose.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.giedrius.slikas.sodybon.compose.base.BOTTOM_INSET_HEIGHT
import com.giedrius.slikas.sodybon.compose.base.CUSTOM_BOTTOM_BAR_HEIGHT
import com.giedrius.slikas.sodybon.screens.login.LoginViewModel
import com.giedrius.slikas.sodybon.utils.Navigation.logBottomNavigationItemClicked
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.koinInject

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
    var selectedScreen by remember { mutableStateOf(BottomBarTabs.Home) }

    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.height(CUSTOM_BOTTOM_BAR_HEIGHT),
            ) {
                // Home tab
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
                        logBottomNavigationItemClicked(BottomBarTabs.Home.name)
                        selectedScreen = BottomBarTabs.Home
                        navigateToTab(
                            navController = navController,
                            tab = BottomBarTabs.Home
                        )
                    },
                )

                // Profile tab
                BottomNavigationItem(
                    icon = {
                        if (uiState.currentProfile?.photoUrl != null) {
                            KamelImage(
                                modifier = Modifier
                                    .size(30.dp)
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
                                Icons.Default.AccountCircle,
                                contentDescription = uiState.currentProfile?.firstName
                                    ?: BottomBarTabs.Profile.name,
                            )
                        }
                    },
                    label = {
                        Text(
                            text = uiState.currentProfile?.firstName ?: BottomBarTabs.Profile.name
                        )
                    },
                    selected = BottomBarTabs.Profile == selectedScreen,
                    onClick = {
                        logBottomNavigationItemClicked(BottomBarTabs.Profile.name)
                        selectedScreen = BottomBarTabs.Profile
                        navigateToTab(
                            navController = navController,
                            tab = BottomBarTabs.Profile
                        )
                    },
                )
            }
        },
        content = {
            MainNavigation(
                navController = navController,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
                    .windowInsetsPadding(WindowInsets.safeDrawing)
                    .padding(bottom = BOTTOM_INSET_HEIGHT),
            )
        }
    )
}