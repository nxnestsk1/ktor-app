plugins {
    kotlin("jvm") version "1.8.0" // Ou uma versão mais recente se necessário
    kotlin("plugin.serialization") version "1.8.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:2.3.0")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.0")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}

