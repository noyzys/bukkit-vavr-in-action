import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("io.github.goooler.shadow") version "8.1.8"
}

group = "dev.noyzys.bukkit.vavr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
    maven { url = uri("https://oss.sonatype.org/content/repositories/central") }
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
}

dependencies {
    // bukkit stuff
    compileOnly("org.spigotmc:spigot-api:1.21-R0.1-SNAPSHOT")
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")

    // fp stuff
    implementation("io.vavr:vavr:0.11.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<ShadowJar> {
    archiveFileName.set("bukkit-vavr.jar")

    exclude(
        "org/intellij/lang/annotations/**",
        "org/jetbrains/annotations/**",
        "org/checkerframework/**",
        "META-INF/**",
        "javax/**"
    )

    minimize()

    val libDirectory = "dev.noyzys.libs"

    listOf(
        "net.kyori",
        "io.vavr",
    ).forEach { toPack ->
        relocate(toPack, "$libDirectory.$toPack")
    }
}