package com.giedrius.slikas.sodybon.previews.property

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.sodybon.R
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.compose.components.PropertyCard
import com.giedrius.slikas.sodybon.screens.home.HomeScreenContent

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