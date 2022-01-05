plugins {
    kotlin("js") version "1.6.10"
}

group = "me.kazokmr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// kotlin-wrappersのGroupIdとArtifactIdの共通文字列テンプレート
fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

// kotlin-wrappers-bom (Kotlin Wrappersの依存関係バージョンの管理をしてくれるBOM)のバージョンを指定する
val kotlinWrappersVersion = "0.0.1-pre.287-kotlin-1.6.10"

dependencies {
    implementation(enforcedPlatform(kotlinw("wrappers-bom:$kotlinWrappersVersion")))
    implementation(kotlinw("react"))
    implementation(kotlinw("react-dom"))
    implementation(kotlinw("react-redux"))
    implementation(kotlinw("react-router-dom"))
    implementation(kotlinw("redux"))
    implementation(kotlinw("styled"))
    implementation(kotlinw("mui"))
    implementation(npm("file-loader", "^6.2.0"))
    implementation(npm("@emotion/react", "^11.7.1"))
    implementation(npm("@emotion/styled", "^11.6.0"))
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.6.0")
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
        binaries.executable()
    }
}