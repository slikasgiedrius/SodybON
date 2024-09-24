package com.giedrius.slikas.sodybon.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.mmk.kmpauth.uihelper.google.GoogleSignInButton
import dev.gitlive.firebase.auth.FirebaseUser

@Composable
fun GoogleLogin(
    onUpdateUser: () -> Unit,
) {
    val onFirebaseResult: (Result<FirebaseUser?>) -> Unit = { result ->
        if (result.isSuccess) {
            Logger.i { "Login with Google successful" }
        } else {
            Logger.i { "Login failed with result ${result.exceptionOrNull()?.message}" }
        }
        onUpdateUser.invoke()
    }

    GoogleButtonUiContainerFirebase(
        onResult = onFirebaseResult,
    ) {
        GoogleSignInButton(
            modifier = Modifier.fillMaxWidth().height(44.dp),
            fontSize = 19.sp
        ) { this.onClick() }
    }
}