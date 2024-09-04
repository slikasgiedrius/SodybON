package com.giedrius.slikas.sodybon.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.data.property.model.Property
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import sodybon.composeapp.generated.resources.Res
import sodybon.composeapp.generated.resources.compose_multiplatform

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinInject(),
) {
    val uiState by viewModel.uiState.collectAsState()

    SodybOnTheme {
        PropertiesList(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .windowInsetsPadding(WindowInsets.safeDrawing),
            properties = uiState.properties,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PropertiesList(
    modifier: Modifier = Modifier,
    properties: List<Property>,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        content = {
            items(properties) {
                Card(
                    modifier = Modifier.padding(bottom = 16.dp),
                    onClick = {
                        Logger.i { "List item clicked: ${it.name}" }
                    }
                ) {
                    Column {
                        if (it.imageUrl.isNullOrEmpty()) {
                            Image(
                                painter = painterResource(Res.drawable.compose_multiplatform),
                                null,
                            )
                        } else {
                            KamelImage(
                                resource = asyncPainterResource(it.imageUrl),
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
                        }
                    }
                }
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = it.name,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    modifier = Modifier.padding(bottom = 24.dp),
                    text = it.address,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Normal,
                )
            }
        })
}

@Preview
@Composable
private fun PreviewPropertiesList() {
    MaterialTheme {
        PropertiesList(
            properties = listOf(
                Property(
                    name = "Property 1",
                    imageUrl = null,
                    address = "Address 1",
                ),
            ),
        )
    }
}