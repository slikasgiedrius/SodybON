package com.giedrius.slikas.sodybon.utils

import com.giedrius.slikas.sodybon.R
import com.giedrius.slikas.sodybon.data.property.model.Property

fun Property.getMockedPropertyImage(): Int {
    return when (this.id) {
        "sod1-prusiskiu-dvaras" -> R.drawable.sod1_main
        "sod2-vantos-lapas-pirciu-rezidencija" -> R.drawable.sod2_main
        "sod3-radviliu-sodyba" -> R.drawable.sod3_main
        else -> R.drawable.compose_multiplatform
    }
}