package com.giedrius.slikas.sodybon.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.giedrius.slikas.sodybon.utils.Property.logListItemClicked
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun PropertyCard(
    property: Property,
    logoNotLoadedPlaceholder: Painter,
    onPropertyClicked: (String) -> Unit,
) {
    Card(
        modifier = Modifier.padding(bottom = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            onPropertyClicked(property.id)
            Logger.logListItemClicked(property.name)
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
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                modifier = Modifier.padding(
                    top = 4.dp,
                    bottom = 4.dp,
                ),
                text = property.address.getShortAddress(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}