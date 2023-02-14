import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm").version("1.7.21")
    kotlin("plugin.serialization").version("1.7.21")
    id("io.ktor.plugin") version "2.2.3"

    application
}

group = "com.yuryandroid"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val coroutines_version: String by project
val ktor_gson_version: String by project
val kotlin_poet_version: String by project
val logback_version: String by project
val ktor_version: String by project

dependencies {
    testImplementation(kotlin("test"))

    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-websockets:$ktor_version")
    implementation("io.ktor:ktor-server-cors:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-server-compression:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

ktor {
    /*deployment {
        port = 8080
    }
    application {
        modules = [ sync_figma_to_gitlab_server.ApplicationKt.module ]
    }*/
}