package com.giedrius.slikas.sodybon

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform