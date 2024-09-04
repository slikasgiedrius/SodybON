package com.giedrius.slikas.sodybon.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.data.article.model.Article
import com.giedrius.slikas.sodybon.data.property.model.Property
import com.giedrius.slikas.sodybon.data.user.model.User
import com.giedrius.slikas.sodybon.getPlatform
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

//    ArticleList(uiState.articles)
//    UserList(uiState.users)
    PropertiesList(
        properties = uiState.properties,
    )
}

@Composable
fun ArticleList(
    articles: List<Article>
) {
    Column(
        modifier = Modifier.background(MaterialTheme.colors.primary)
    ) {
        Text(getPlatform().name)
        LazyColumn {
            items(articles) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    Text(
                        text = it.author ?: "No author",
                        modifier = Modifier
                            .background(MaterialTheme.colors.primaryVariant)
                            .padding(16.dp),
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun UserList(
    users: List<User>
) {
    Column(
        modifier = Modifier.background(MaterialTheme.colors.primary)
    ) {
        Text(getPlatform().name)
        LazyColumn {
            items(users) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    Text(
                        text = it.fullName,
                        modifier = Modifier.padding(16.dp),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PropertiesList(
    properties: List<Property>,
) {
    LazyColumn(
        content = {
            items(properties) {
                Card(
                    onClick = {
                        Logger.i { "List item clicked" }
                    }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = it.name,
                            fontWeight = FontWeight.Bold
                        )
                        if (it.imageUrl.isNullOrEmpty()) {
                            Image(
                                painter = painterResource(Res.drawable.compose_multiplatform),
                                null,
                            )
                        } else {
                            KamelImage(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp)),
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