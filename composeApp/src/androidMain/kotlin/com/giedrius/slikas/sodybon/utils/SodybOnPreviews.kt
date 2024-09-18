package com.giedrius.slikas.sodybon.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.giedrius.slikas.sodybon.R
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.ui.PropertiesList

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewPropertiesList() {
    SodybOnTheme {
        PropertiesList(
            properties = getMockedPropertyList(),
            logoNotLoadedPlaceholder = painterResource(getRandomMockedPropertyImage())
        )
    }
}

fun getRandomMockedPropertyImage(): Int {
    val listOfMockedPropertyImages = listOf(
        R.drawable.sod1_main,
        R.drawable.sod2_main,
        R.drawable.sod3_main
    )
    return listOfMockedPropertyImages.shuffled().random()
}