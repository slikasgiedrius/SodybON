package com.giedrius.slikas.sodybon.screens.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.compose.components.PropertyCard
import com.giedrius.slikas.sodybon.data.property.model.Property
import com.giedrius.slikas.sodybon.utils.extensions.topLevelFullScreenBackground
import dev.materii.pullrefresh.PullRefreshIndicator
import dev.materii.pullrefresh.PullRefreshLayout
import dev.materii.pullrefresh.rememberPullRefreshState
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import sodybon.composeapp.generated.resources.Res
import sodybon.composeapp.generated.resources.compose_multiplatform

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
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
            modifier = modifier.topLevelFullScreenBackground(MaterialTheme.colorScheme.background),
            state = pullRefreshState,
            indicator = {
                PullRefreshIndicator(
                    state = pullRefreshState,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            }
        ) {
            if (uiState.getPropertiesException != null) {
                // Handle error state
                Text(uiState.getPropertiesException!!.name)
            } else {
                HomeScreenContent(
                    properties = uiState.properties,
                    onPropertyClicked = onPropertyClicked
                )
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    onPropertyClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    properties: List<Property>,
    logoNotLoadedPlaceholder: Painter = painterResource(Res.drawable.compose_multiplatform),
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        content = {
            itemsIndexed(properties.filter { it.isEnabled }) { index, property ->
                PropertyCard(
                    modifier = Modifier
                        .padding(bottom = if (index == properties.size - 1) 8.dp else 28.dp), //Set smaller padding if last item in the list
                    property = property,
                    logoNotLoadedPlaceholder = logoNotLoadedPlaceholder,
                    onPropertyClicked = onPropertyClicked
                )
            }
        }
    )
}