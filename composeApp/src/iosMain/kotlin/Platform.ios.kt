import platform.UIKit.UIDevice
import utility.ActualPlatform

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

class IOSPlatformName(override val name: ActualPlatform) : PlatformName

actual fun getPlatform(): Platform = IOSPlatform()
actual fun platformName(): PlatformName = IOSPlatformName(ActualPlatform.IOS)