package com.giedrius.slikas.sodybon.screens.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.giedrius.slikas.sodybon.compose.components.GoogleLogin
import com.giedrius.slikas.sodybon.data.profile.model.Profile
import com.giedrius.slikas.sodybon.navigation.NavigationBarTabs
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.koinInject

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = koinInject(),
) {
    val uiState by loginViewModel.uiState.collectAsState()

    SodybOnTheme {
        LoginScreenContent(
            currentProfile = uiState.currentProfile,
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .windowInsetsPadding(WindowInsets.safeDrawing),
            onGoogleLoginClicked = { loginViewModel.getCurrentProfile() },
        )
    }
}


@Composable
fun LoginScreenContent(
    currentProfile: Profile?,
    modifier: Modifier = Modifier,
    onGoogleLoginClicked: () -> Unit,
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
                    GoogleLogin(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        onUpdateUser = { onGoogleLoginClicked() },
                    )
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
                                    ?: NavigationBarTabs.Profile.name,
                            )
                        }
                        Text(
                            text = currentProfile.fullName ?: "Not logged in",
                            style = MaterialTheme.typography.titleLarge,
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
            Text("Bottom Content")
        }
    }
}