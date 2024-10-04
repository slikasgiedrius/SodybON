package com.giedrius.slikas.sodybon.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
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
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.compose.base.BOTTOM_INSET_HEIGHT
import com.giedrius.slikas.sodybon.screens.feature.login.LoginViewModel
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
    var selectedTabItem by remember { mutableStateOf(BottomBarTabs.Home) }

    when (selectedTabItem) {
        BottomBarTabs.Home -> Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(MaterialTheme.colors.background)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(MaterialTheme.colors.primary)
            )
        }

        BottomBarTabs.Profile -> Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(MaterialTheme.colors.secondary)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(MaterialTheme.colors.primary)
            )
        }
    }

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        bottomBar = {
            BottomNavigation {
                // Home tab
                BottomNavigationItem(
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = BottomBarTabs.Home.name
                        )
                    },
                    label = { Text(BottomBarTabs.Home.name) },
                    selected = BottomBarTabs.Home == selectedTabItem,
                    onClick = {
                        Logger.logBottomNavigationItemClicked(BottomBarTabs.Home.name)
                        selectedTabItem = BottomBarTabs.Home
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
                    selected = BottomBarTabs.Profile == selectedTabItem,
                    onClick = {
                        Logger.logBottomNavigationItemClicked(BottomBarTabs.Profile.name)
                        selectedTabItem = BottomBarTabs.Profile
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
                    .padding(bottom = BOTTOM_INSET_HEIGHT),
            )
        }
    )
}