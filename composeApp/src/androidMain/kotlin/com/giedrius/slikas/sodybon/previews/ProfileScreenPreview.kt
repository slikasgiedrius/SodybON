package com.giedrius.slikas.sodybon.previews

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.screens.profile.ProfileScreenContent
import com.giedrius.slikas.sodybon.utils.user.getMockedUser

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewProfileScreenContent() {
    SodybOnTheme {
        ProfileScreenContent(
            currentUser = getMockedUser(),
            onUpdateUserClicked = {},
            onSignOutClicked = {},
        )
    }
}