package com.giedrius.slikas.sodybon.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.giedrius.slikas.sodybon.screens.home.HomeViewModel
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import com.mmk.kmpauth.uihelper.google.GoogleSignInButton
import com.mmk.kmpauth.uihelper.google.GoogleSignInButtonIconOnly
import dev.gitlive.firebase.auth.FirebaseUser
import org.koin.compose.koinInject

@Composable
fun LoginScreen(
    viewModel: HomeViewModel = koinInject(),
) {
    GoogleAuthProvider.create(
        credentials = GoogleAuthCredentials(
            serverId = "311568248602-advol62mglp8dt0osotd0o1dbodcj9ta.apps.googleusercontent.com"
        )
    )

    MaterialTheme {
        Column(
            Modifier.fillMaxSize().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {

            var signedInUserName: String by remember { mutableStateOf("") }
            val onFirebaseResult: (Result<FirebaseUser?>) -> Unit = { result ->
                if (result.isSuccess) {
                    val firebaseUser = result.getOrNull()
                    signedInUserName =
                        firebaseUser?.displayName ?: firebaseUser?.email ?: "Null User"
                } else {
                    signedInUserName = "Null User"
                    println("Error Result: ${result.exceptionOrNull()?.message}")
                }
                viewModel.updateUser()
            }
            Column {
                Text(
                    text = "UserRepository ${viewModel.uiState.value.currentUser?.displayName}",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = signedInUserName,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start,
                )
            }

            Divider(modifier = Modifier.fillMaxWidth().padding(16.dp))
            //Google Sign-In Button and authentication with Firebase
            GoogleButtonUiContainerFirebase(onResult = onFirebaseResult) {
                GoogleSignInButton(
                    modifier = Modifier.fillMaxWidth().height(44.dp),
                    fontSize = 19.sp
                ) { this.onClick() }
            }

            Divider(modifier = Modifier.fillMaxWidth().padding(16.dp))
            //Google Sign-In IconOnly Button and authentication with Firebase
            GoogleButtonUiContainerFirebase(onResult = onFirebaseResult) {
                GoogleSignInButtonIconOnly(onClick = { this.onClick() })
            }
        }
    }
}