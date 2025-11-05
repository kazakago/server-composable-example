plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinx.rpc)
    alias(libs.plugins.ktor)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    application
}

group = "kotlinx.rpc.sample"
version = "1.0.0"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.cors)
    implementation(libs.kotlinx.rpc.krpc.server)
    implementation(libs.kotlinx.rpc.krpc.serialization.json)
    implementation(libs.kotlinx.rpc.krpc.ktor.server)
    implementation(libs.molecule)
}
