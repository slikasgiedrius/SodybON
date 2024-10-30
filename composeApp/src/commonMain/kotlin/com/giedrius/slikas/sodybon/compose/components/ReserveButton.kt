package com.giedrius.slikas.sodybon.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.navigation.CUSTOM_BOTTOM_BAR_HEIGHT
import com.giedrius.slikas.sodybon.utils.Property.logClickOnReserveButton
import com.giedrius.slikas.sodybon.utils.Property.logUnableToIdentifyPropertyWhenClickingReserve

@Composable
fun ReserveButton(
    propertyName: String?,
    onReserveButtonClicked: () -> Unit
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier.height(CUSTOM_BOTTOM_BAR_HEIGHT),
    ) {
        Box(
            modifier = Modifier
                .clickable {
                    if (!propertyName.isNullOrEmpty()) {
                        onReserveButtonClicked()
                        Logger.logClickOnReserveButton(
                            propertyName = propertyName
                        )
                    } else {
                        Logger.logUnableToIdentifyPropertyWhenClickingReserve()
                    }
                }
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Reserve",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}