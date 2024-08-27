package com.giedrius.slikas.sodybon

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.giedrius.slikas.sodybon.ui.HomeScreen
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

@Composable
fun App() {
    MaterialTheme {
        HomeScreen()
    }
}