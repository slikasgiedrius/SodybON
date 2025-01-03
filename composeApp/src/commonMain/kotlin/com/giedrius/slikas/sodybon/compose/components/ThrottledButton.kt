package com.giedrius.slikas.sodybon.compose.components

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ThrottledButton(
    onClick: () -> Unit,
    throttleDelayMillis: Long = 1000, // Default to 1 second delay
    content: @Composable () -> Unit
) {
    var isClickable by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    Button(
        onClick = {
            if (isClickable) {
                isClickable = false
                onClick()
                scope.launch {
                    delay(throttleDelayMillis)
                    isClickable = true
                }
            }
        },
        enabled = isClickable
    ) {
        content()
    }
}