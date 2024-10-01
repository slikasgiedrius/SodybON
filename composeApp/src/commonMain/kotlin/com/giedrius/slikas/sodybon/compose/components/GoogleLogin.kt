package com.giedrius.slikas.sodybon.compose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import com.giedrius.slikas.sodybon.utils.Login.logLoginFailed
import com.giedrius.slikas.sodybon.utils.Login.logLoginWithGoogleClicked
import com.giedrius.slikas.sodybon.utils.Login.logLoginWithGoogleSuccessful
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.mmk.kmpauth.uihelper.google.GoogleSignInButton
import dev.gitlive.firebase.auth.FirebaseUser

@Composable
fun GoogleLogin(
    modifier: Modifier = Modifier,
    onUpdateUser: () -> Unit,
) {
    val onFirebaseResult: (Result<FirebaseUser?>) -> Unit = { result ->
        if (result.isSuccess) {
            Logger.logLoginWithGoogleSuccessful()
        } else {
            Logger.logLoginFailed(errorMessage = result.exceptionOrNull()?.message.toString())
        }
        onUpdateUser.invoke()
    }

    GoogleButtonUiContainerFirebase(
        modifier = modifier,
        onResult = onFirebaseResult,
    ) {
        GoogleSignInButton(
            modifier = Modifier.fillMaxWidth().height(44.dp),
            fontSize = 19.sp
        ) {
            Logger.logLoginWithGoogleClicked()
            this.onClick()
        }
    }
}