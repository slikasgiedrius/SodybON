package com.giedrius.slikas.sodybon.screens.feature.detailed_property.date_selection

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.giedrius.slikas.sodybon.utils.extensions.toNormalDate

@Composable
fun DateSelectionBottomSheetItem(
    date: Long?,
    fromText: String,
    endText: String,
) {
    if (date != null) {
        Text(text = "$fromText ${date.toNormalDate()}")
    } else {
        Text(text = endText)
    }
}