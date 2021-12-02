plugins {
    kotlin("js") version "1.6.0"
}

group = "me.kazokmr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.274-kotlin-1.6.0")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.274-kotlin-1.6.0")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-redux:7.2.6-pre.274-kotlin-1.6.0")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:6.0.2-pre.274-kotlin-1.6.0")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-redux:4.1.2-pre.274-kotlin-1.6.0")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-styled:5.3.3-pre.274-kotlin-1.6.0")
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
        useCommonJs()
    }
}