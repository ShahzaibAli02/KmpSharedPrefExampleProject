package org.shahzaib.kmpsharedpref

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform