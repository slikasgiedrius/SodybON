package com.giedrius.slikas.sodybon.screens.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.giedrius.slikas.sodybon.compose.base.SodybOnTheme
import com.giedrius.slikas.sodybon.compose.components.ProfilePicture
import com.giedrius.slikas.sodybon.data.profile.model.Profile
import com.giedrius.slikas.sodybon.screens.feature.login.LoginViewModel
import com.giedrius.slikas.sodybon.utils.extensions.topLevelFullScreenBackground
import org.koin.compose.koinInject

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = koinInject(),
    profileViewModel: ProfileViewModel = koinInject(),
) {
    val loginUiState by loginViewModel.uiState.collectAsState()

    SodybOnTheme {
        ProfileScreenContent(
            currentProfile = loginUiState.currentProfile,
            modifier = modifier
                .topLevelFullScreenBackground(MaterialTheme.colorScheme.background),
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

        //User avatar with first and last name
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(
                currentProfile = currentProfile
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = currentProfile?.firstName ?: "No First Name",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = currentProfile?.lastName ?: "No Last Name",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        }

        //Sign out
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 4.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = { onSignOutClicked() }
            ) {
                Text(
                    text = "Sign out",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}