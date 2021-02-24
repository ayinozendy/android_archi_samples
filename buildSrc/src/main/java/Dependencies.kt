object Versions {
    const val sqlDelight = "1.4.4"

    const val robolectric = "4.4"

    object AndroidX {
        const val testExt = "1.1.2"
        const val test = "1.3.0"
    }

    const val kotlin = "1.4.30"
}

object SqlDelight {
    const val iosDriver = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
    const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    const val driver = "com.squareup.sqldelight:sqlite-driver:${Versions.sqlDelight}"
    const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
}

object Dependencies {
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
}

object AndroidXTest {
    const val core = "androidx.test:core:${Versions.AndroidX.test}"
    const val junit = "androidx.test.ext:junit:${Versions.AndroidX.testExt}"
}

object Kotlin {
    const val testCommon = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
    const val annotationTest = "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
}
