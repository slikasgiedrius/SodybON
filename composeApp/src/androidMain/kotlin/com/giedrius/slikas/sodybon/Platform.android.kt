package com.giedrius.slikas.sodybon

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getPlatformBottomBarPadding(): Int = 32 //BOTTOM_INSET_HEIGHT