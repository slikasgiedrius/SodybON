package com.giedrius.slikas.sodybon.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.data.property.model.Property
import com.giedrius.slikas.sodybon.data.property.model.getShortAddress
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PropertyCard(
    property: Property,
    logoNotLoadedPlaceholder: Painter,
    onPropertyClicked: (String) -> Unit,
) {
    Card(
        modifier = Modifier.padding(bottom = 20.dp),
        backgroundColor = MaterialTheme.colors.background,
        shape = MaterialTheme.shapes.medium,
        elevation = 0.dp,
        onClick = {
            onPropertyClicked(property.id)
            Logger.i { "List item clicked: ${property.name}" }
        }
    ) {
        Column {
            if (property.imageUrl.isNullOrEmpty()) {
                Image(
                    painter = logoNotLoadedPlaceholder,
                    null,
                )
            } else {
                KamelImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium),
                    resource = asyncPainterResource(property.imageUrl),
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
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = property.name,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                modifier = Modifier.padding(
                    top = 4.dp,
                    bottom = 4.dp,
                ),
                text = property.address.getShortAddress(),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}