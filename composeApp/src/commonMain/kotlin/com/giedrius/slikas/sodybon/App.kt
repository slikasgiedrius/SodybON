package com.giedrius.slikas.sodybon

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import sodybon.composeapp.generated.resources.Res
import sodybon.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {
    MaterialTheme {
        Column(Modifier.fillMaxWidth()) {
            var list by remember { mutableStateOf(listOf<User>()) }
            LaunchedEffect(Unit) {
                list = getUsers()
            }
            Text(list.toString())
        }
    }
}

suspend fun getUsers(): List<User> {
    val firebaseFirestore = Firebase.firestore
    try {
        val userResponse =
            firebaseFirestore.collection("USERS").get()
        return userResponse.documents.map {
            it.data()
        }
    } catch (e: Exception) {
        throw e
    }
}

@Composable
fun UserItem(user: User) {
    Column {
        Text(
            text = user.name
        )
        Text(
            text = user.age.toString()
        )
    }
}