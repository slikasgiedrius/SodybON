package com.giedrius.slikas.sodybon.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.sodybon.data.profile.model.Profile
import com.giedrius.slikas.sodybon.navigation.BottomBarTabs
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

val PROFILE_PICTURE_SIZE = 72.dp

@Composable
fun ProfilePicture(
    modifier: Modifier = Modifier,
    currentProfile: Profile?,
) {
    if (currentProfile?.photoUrl != null) {
        KamelImage(modifier = modifier.size(PROFILE_PICTURE_SIZE).clip(CircleShape),
            resource = asyncPainterResource(currentProfile.photoUrl),
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
    } else {
        Icon(
            modifier = Modifier.size(PROFILE_PICTURE_SIZE),
            imageVector = Icons.Default.AccountCircle,
            contentDescription = currentProfile?.firstName ?: BottomBarTabs.Profile.name,
        )
    }
}