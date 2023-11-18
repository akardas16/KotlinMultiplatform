import utility.ActualPlatform

interface Platform {
    val name: String
}

interface PlatformName{
    val name:ActualPlatform
}

expect fun getPlatform(): Platform

expect fun platformName(): PlatformName