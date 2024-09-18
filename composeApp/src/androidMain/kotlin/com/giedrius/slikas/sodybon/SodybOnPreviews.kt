package com.giedrius.slikas.sodybon

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.ui.PropertiesList
import com.giedrius.slikas.sodybon.utils.getMockedPropertyList

@Preview(showBackground = true)
@Composable
private fun PreviewPropertiesList() {
    SodybOnTheme {
        PropertiesList(
            properties = getMockedPropertyList(),
            logoNotLoadedPlaceholder = painterResource(id = R.drawable.compose_multiplatform)
        )
    }
}