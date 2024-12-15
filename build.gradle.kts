plugins {
    application
    kotlin("jvm") version "1.8.0" // ou a versão que você está usando
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:2.3.0") // Verifique a versão mais recente
    implementation("io.ktor:ktor-server-netty:2.3.0") // Para usar o Netty como servidor
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.0") // Para serialização JSON
    implementation("io.ktor:ktor-server-content-negotiation:2.3.0") // Para Content Negotiation
    testImplementation("io.ktor:ktor-server-tests:2.3.0") // Para testes
}

application {
    mainClass.set("com.example.ApplicationKt") // Altere para o seu pacote
}