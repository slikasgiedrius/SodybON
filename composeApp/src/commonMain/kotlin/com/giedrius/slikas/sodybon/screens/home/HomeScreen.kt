package com.giedrius.slikas.sodybon.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.compose.components.PropertyCard
import com.giedrius.slikas.sodybon.data.property.model.Property
import dev.materii.pullrefresh.PullRefreshIndicator
import dev.materii.pullrefresh.PullRefreshLayout
import dev.materii.pullrefresh.rememberPullRefreshState
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import sodybon.composeapp.generated.resources.Res
import sodybon.composeapp.generated.resources.compose_multiplatform

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinInject(),
    onPropertyClicked: (String) -> Unit,
) {
    val uiState by homeViewModel.uiState.collectAsState()

    val isRefreshing by remember {
        mutableStateOf(false)
    }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { homeViewModel.updateProperties() }
    )

    SodybOnTheme {
        PullRefreshLayout(
            state = pullRefreshState,
            indicator = {
                PullRefreshIndicator(
                    state = pullRefreshState,
                    contentColor = MaterialTheme.colors.primary
                )
            }
        ) {
            PropertiesList(
                properties = uiState.properties,
                onPropertyClicked = onPropertyClicked
            )
        }
    }
}


@Composable
fun PropertiesList(
    modifier: Modifier = Modifier,
    properties: List<Property>,
    logoNotLoadedPlaceholder: Painter = painterResource(Res.drawable.compose_multiplatform),
    onPropertyClicked: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        content = {
            items(properties.filter { it.isEnabled }) {
                PropertyCard(
                    property = it,
                    logoNotLoadedPlaceholder = logoNotLoadedPlaceholder,
                    onPropertyClicked = onPropertyClicked
                )
            }
        })
}