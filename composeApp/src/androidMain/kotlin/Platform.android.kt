import android.os.Build
import utility.ActualPlatform

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

class AndroidPlatformName(override val name: ActualPlatform) : PlatformName
actual fun platformName():PlatformName = AndroidPlatformName(ActualPlatform.ANDROID)