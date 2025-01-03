package com.giedrius.slikas.sodybon.previews.feature.property

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.giedrius.slikas.sodybon.R
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.screens.feature.home.HomeScreenContent

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewPropertiesList() {
    SodybOnTheme {
        HomeScreenContent(
            onPropertyClicked = {},
            properties = getMockedPropertyList(),
            logoNotLoadedPlaceholder = painterResource(R.drawable.sod1_main),
        )
    }
}