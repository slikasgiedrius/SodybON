package com.giedrius.slikas.sodybon.previews.feature.detailed_property

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.screens.feature.detailed_property.DetailedPropertyScreenContent

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewDetailedPropertyScreenContent() {
    SodybOnTheme {
        DetailedPropertyScreenContent(
            property = getMockedDetailedProperty(),
            onNavigateBack = {}
        )
    }
}