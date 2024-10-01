package com.giedrius.slikas.sodybon.screens.feature.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.navigation.BottomBarTabs
import com.giedrius.slikas.sodybon.data.profile.model.Profile
import com.giedrius.slikas.sodybon.screens.feature.login.LoginViewModel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.koinInject

@Composable
fun ProfileScreen(
    loginViewModel: LoginViewModel = koinInject(),
    profileViewModel: ProfileViewModel = koinInject(),
    modifier: Modifier = Modifier,
) {
    val loginUiState by loginViewModel.uiState.collectAsState()

    SodybOnTheme {
        ProfileScreenContent(
            currentProfile = loginUiState.currentProfile,
            modifier = modifier,
            onSignOutClicked = { loginViewModel.signOut() },
        )
    }
}

@Composable
fun ProfileScreenContent(
    currentProfile: Profile?,
    modifier: Modifier = Modifier,
    onSignOutClicked: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        // top row
        Row(
            modifier = Modifier.fillMaxWidth().height(50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                if (currentProfile == null) {
                    //Shouldn't be possible
                } else {
                    Row {
                        if (currentProfile.photoUrl != null) {
                            KamelImage(modifier = Modifier.size(48.dp).clip(CircleShape),
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
                                Icons.Default.AccountCircle,
                                contentDescription = currentProfile.firstName
                                    ?: BottomBarTabs.Profile.name,
                            )
                        }
                        Text(
                            text = currentProfile.fullName ?: "Not logged in",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }

        }

        // content row
        Row(
            modifier = Modifier.fillMaxWidth().weight(0.5F),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Some Other Contents")
        }

        // bottom row
        Row(
            modifier = Modifier.fillMaxWidth().height(100.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { onSignOutClicked() }) {
                Text("Sign out")
            }
        }
    }
}