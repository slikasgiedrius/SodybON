package com.giedrius.slikas.sodybon.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.compose.components.BottomBarItemText
import com.giedrius.slikas.sodybon.compose.components.ReserveButton
import com.giedrius.slikas.sodybon.screens.feature.detailed_property.DetailedPropertyViewModel
import com.giedrius.slikas.sodybon.screens.feature.login.LoginViewModel
import com.giedrius.slikas.sodybon.utils.Navigation.logBottomNavigationItemClicked
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.koinInject
import sodybon.composeapp.generated.resources.Res
import sodybon.composeapp.generated.resources.door
import sodybon.composeapp.generated.resources.door_filled

private val BOTTOM_NAV_ICON_SIZE = 30.dp
private const val HOME_ICON_CROSSFADE_DURATION = 300
internal val CUSTOM_BOTTOM_BAR_HEIGHT = 100.dp //Increased for an experiment

enum class NavigationBarTabs {
    Home,
    Profile,
}

@Composable
fun NavigationBar(
    loginViewModel: LoginViewModel = koinInject(),
    detailedPropertyViewModel: DetailedPropertyViewModel = koinInject(),
) {
    val loginUiState by loginViewModel.uiState.collectAsState()

    val navController = rememberNavController()
    var selectedScreen by remember { mutableStateOf(NavigationBarTabs.Home) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            when (currentRoute) {
                NavigationBarTabs.Home.name, NavigationBarTabs.Profile.name -> {
                    BottomAppBar(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.height(CUSTOM_BOTTOM_BAR_HEIGHT)
                    ) {
                        // Home tab
                        NavigationBarItem(
                            icon = {
                                Crossfade(
                                    targetState = selectedScreen == NavigationBarTabs.Home,
                                    animationSpec = tween(durationMillis = HOME_ICON_CROSSFADE_DURATION)
                                ) { isHomeSelected ->
                                    if (isHomeSelected) {
                                        Icon(
                                            modifier = Modifier.size(BOTTOM_NAV_ICON_SIZE),
                                            imageVector = vectorResource(Res.drawable.door_filled),
                                            contentDescription = NavigationBarTabs.Home.name,
                                        )
                                    } else {
                                        Icon(
                                            modifier = Modifier.size(BOTTOM_NAV_ICON_SIZE),
                                            imageVector = vectorResource(Res.drawable.door),
                                            contentDescription = NavigationBarTabs.Home.name,
                                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    }
                                }
                            },
                            label = {
                                BottomBarItemText(
                                    text = NavigationBarTabs.Home.name
                                )
                            },
                            selected = NavigationBarTabs.Home == selectedScreen,
                            onClick = {
                                Logger.logBottomNavigationItemClicked(NavigationBarTabs.Home.name)
                                selectedScreen = NavigationBarTabs.Home
                                navigateToTab(
                                    navController = navController,
                                    tab = NavigationBarTabs.Home
                                )
                            },
                        )

                        // Profile tab
                        NavigationBarItem(
                            icon = {
                                if (loginUiState.currentProfile?.photoUrl != null) {
                                    KamelImage(
                                        modifier = Modifier
                                            .size(BOTTOM_NAV_ICON_SIZE)
                                            .clip(CircleShape),
                                        resource = asyncPainterResource(loginUiState.currentProfile?.photoUrl!!),
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
                                        contentDescription = loginUiState.currentProfile?.firstName
                                            ?: NavigationBarTabs.Profile.name,
                                    )
                                }
                            },
                            label = {
                                BottomBarItemText(
                                    text = loginUiState.currentProfile?.firstName
                                        ?: NavigationBarTabs.Profile.name
                                )
                            },
                            selected = NavigationBarTabs.Profile == selectedScreen,
                            onClick = {
                                Logger.logBottomNavigationItemClicked(NavigationBarTabs.Profile.name)
                                selectedScreen = NavigationBarTabs.Profile
                                navigateToTab(
                                    navController = navController,
                                    tab = NavigationBarTabs.Profile
                                )
                            },
                        )
                    }
                }

                MainNavigationDirections.Routes.DETAILED_PROPERTY -> {
                    val propertyName: String? = navBackStackEntry?.arguments?.getString(
                        MainNavigationDirections.DetailedPropertyArgs.PROPERTY_ID
                    )
                    ReserveButton(
                        propertyName = propertyName,
                        onReserveButtonClicked = {
                            detailedPropertyViewModel.setShowDatePicker(true)
                        }
                    )
                }
            }
        },
        content = { innerPadding ->
            MainNavigation(
                modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                navController = navController,
            )
        }
    )
}