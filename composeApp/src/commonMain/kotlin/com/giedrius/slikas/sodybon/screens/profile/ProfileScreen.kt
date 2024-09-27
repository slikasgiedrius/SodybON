package com.giedrius.slikas.sodybon.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.compose.components.GoogleLogin
import com.giedrius.slikas.sodybon.compose.navigation.BottomBarTabs
import com.giedrius.slikas.sodybon.screens.login.LoginViewModel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.koinInject

@Composable
fun ProfileScreen(
    loginViewModel: LoginViewModel = koinInject(),
    modifier: Modifier = Modifier,
) {
    val uiState by loginViewModel.uiState.collectAsState()

    SodybOnTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {

            // top row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    if (uiState.currentUser == null) {
                        GoogleLogin(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            onUpdateUser = { loginViewModel.updateUser() },
                        )
                    } else {
                        Row {
                            if (uiState.currentUser?.photoUrl != null) {
                                KamelImage(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape),
                                    resource = asyncPainterResource(uiState.currentUser?.photoUrl!!),
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
                                    contentDescription = uiState.currentUser?.firstName
                                        ?: BottomBarTabs.Profile.name,
                                )
                            }
                            Text(
                                text = uiState.currentUser?.fullName ?: "Not logged in",
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                }

            }

            // content row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5F),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Some Other Contents")
            }

            // bottom row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { loginViewModel.signOut() }) {
                    Text("Sign out")
                }
            }

        }
    }
}