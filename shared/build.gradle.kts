plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.4.30"
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    // Block to solve SqlDelight iOS driver issue
    // from https://github.com/cashapp/sqldelight/issues/2044#issuecomment-721299517.
    val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
    if (onPhone) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(SqlDelight.runtime)
                implementation(Ktor.clientCore)
                implementation(Ktor.clientCio)
                implementation(Ktor.clientSerialization)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(SqlDelight.androidDriver)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(SqlDelight.iosDriver)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13")
                implementation(SqlDelight.driver)
                implementation(AndroidXTest.core)
                implementation(AndroidXTest.junit)
                implementation(Dependencies.robolectric)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(Kotlin.testCommon)
                implementation(Kotlin.annotationTest)
                implementation(Ktor.clientMock)
            }
        }
    }
}

sqldelight {
    database("KmmAppDatabase") { // This will be the name of the generated database class.
        packageName = "kmm.queries.shared"
    }
}

android {
    compileSdkVersion(30)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
    }
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}
