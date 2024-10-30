package com.giedrius.slikas.sodybon.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.compose.components.ReserveButton
import com.giedrius.slikas.sodybon.screens.feature.login.LoginViewModel
import com.giedrius.slikas.sodybon.utils.Navigation.logBottomNavigationItemClicked
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.koinInject
import sodybon.composeapp.generated.resources.Res
import sodybon.composeapp.generated.resources.door
import sodybon.composeapp.generated.resources.door_filled

private val BOTTOM_NAV_ICON_SIZE = 30.dp
internal val CUSTOM_BOTTOM_BAR_HEIGHT = 100.dp //Increased for an experiment

enum class BottomBarTabs {
    Home,
    Profile,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    loginViewModel: LoginViewModel = koinInject(),
) {
    val uiState by loginViewModel.uiState.collectAsState()
    var showDatePicker by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }

    val navController = rememberNavController()
    var selectedScreen by remember { mutableStateOf(BottomBarTabs.Home) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            when (currentRoute) {
                BottomBarTabs.Home.name, BottomBarTabs.Profile.name -> {
                    BottomAppBar(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.height(CUSTOM_BOTTOM_BAR_HEIGHT)
                    ) {
                        // Home tab
                        NavigationBarItem(
                            icon = {
                                AnimatedVisibility(
                                    visible = selectedScreen == BottomBarTabs.Home,
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    Icon(
                                        modifier = Modifier.size(BOTTOM_NAV_ICON_SIZE),
                                        imageVector = vectorResource(Res.drawable.door_filled),
                                        contentDescription = BottomBarTabs.Home.name,
                                    )
                                }
                                AnimatedVisibility(
                                    visible = selectedScreen != BottomBarTabs.Home,
                                    enter = fadeIn(),
                                    exit = fadeOut()
                                ) {
                                    Icon(
                                        modifier = Modifier.size(BOTTOM_NAV_ICON_SIZE),
                                        imageVector = vectorResource(Res.drawable.door),
                                        contentDescription = BottomBarTabs.Home.name,
                                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                    )
                                }
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

                MainNavigationDirections.Routes.DETAILED_PROPERTY -> {
                    val propertyName: String? = navBackStackEntry?.arguments?.getString(
                        MainNavigationDirections.DetailedPropertyArgs.PROPERTY_ID
                    )
                    ReserveButton(
                        propertyName = propertyName,
                        onReserveButtonClicked = {
                            showDatePicker = true
                        }
                    )
                    val datePickerState = rememberDateRangePickerState(
                        initialSelectedStartDateMillis = Clock.System.now().toEpochMilliseconds(),
                        initialDisplayMode = DisplayMode.Picker,
                        selectableDates = object : SelectableDates {
                            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                                val currentDateMillis = Clock.System.now().toEpochMilliseconds()
                                return utcTimeMillis >= currentDateMillis - 86400000 // Allow only dates greater than or equal to today
                            }

                            override fun isSelectableYear(year: Int): Boolean {
                                return true
                            }
                        }
                    )
                    if (showDatePicker) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                        ) {

                            if (showDatePicker) {
                                DatePickerDialog(
                                    onDismissRequest = { showDatePicker = false },
                                    confirmButton = {
                                        TextButton(
                                            onClick = {
                                                showDatePicker = false
                                                showBottomSheet = true
                                            },
                                        ) {
                                            Text("Ok")
                                        }
                                    },
                                    dismissButton = {
                                        TextButton(
                                            onClick = { showDatePicker = false },
                                        ) {
                                            Text("Cancel")
                                        }
                                    }
                                ) {
                                    DateRangePicker(
                                        state = datePickerState,
                                    )
                                }
                            }
                        }
                    }
                    if (showBottomSheet) {
                        ModalBottomSheet(
                            onDismissRequest = { showBottomSheet = false }
                        ) {
                            if (datePickerState.selectedStartDateMillis != null) {
                                val fromInstant =
                                    Instant.fromEpochMilliseconds(datePickerState.selectedStartDateMillis!!)
                                val fromDateTime =
                                    fromInstant.toLocalDateTime(TimeZone.currentSystemDefault())
                                val from = LocalDate(
                                    fromDateTime.year,
                                    fromDateTime.monthNumber,
                                    fromDateTime.dayOfMonth
                                )
                                Text(text = "From $from")
                            } else {
                                Text(text = "Start date not selected")
                            }

                            if (datePickerState.selectedEndDateMillis != null) {
                                val toInstant =
                                    Instant.fromEpochMilliseconds(datePickerState.selectedEndDateMillis!!)
                                val toDateTime =
                                    toInstant.toLocalDateTime(TimeZone.currentSystemDefault())
                                val to = LocalDate(
                                    toDateTime.year,
                                    toDateTime.monthNumber,
                                    toDateTime.dayOfMonth
                                )

                                Text(text = "To $to")
                            } else {
                                Text(text = "End date not selected")
                            }
                        }
                    }
                }

                else -> {
                    //Keep it empty
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