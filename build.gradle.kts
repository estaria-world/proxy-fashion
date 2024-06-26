import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.0"
    `maven-publish`
}

group = "world.estaria"
version = "1.0.1"

repositories {
    mavenCentral()

    // minecraft repositories
    maven("https://repo.papermc.io/repository/maven-public/")

    // estaria dependencies
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/estaria-world/kube-configmap-kit")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/estaria-world/proxy-manager")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/estaria-world/server-manager")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/estaria-world/translation")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
}

dependencies {
    // avionik dependencies
    compileOnly("world.avionik:config-kit:1.0.2")
    compileOnly("world.avionik:minecraft-common:1.0.1")

    // estaria dependencies
    compileOnly("world.estaria:kube-configmap-kit:1.0.4")
    compileOnly("world.estaria:proxy-manager-api:1.0.1")
    compileOnly("world.estaria:server-manager-api:1.0.2")
    compileOnly("world.estaria:translation-api:1.1.0")

    // adventure dependencies
    val adventureVersion = "4.16.0"
    compileOnly("net.kyori:adventure-api:$adventureVersion")
    compileOnly("net.kyori:adventure-text-minimessage:$adventureVersion")

    // velocity dependencies
    compileOnly("com.velocitypowered:velocity-api:3.3.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.3.0-SNAPSHOT")

    // kubernetes dependencies
    compileOnly("io.fabric8:kubernetes-client:6.12.1")

    // kotlin dependencies
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
}

tasks.named("shadowJar", ShadowJar::class) {
    mergeServiceFiles()
}